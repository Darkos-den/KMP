package com.company.projectName.login

import com.company.projectName.login.model.StatusProcessor
import com.company.projectName.login.model.WithoutValidationField
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.mvu.Reducer
import com.darkos.mvu.map
import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.None
import com.darkos.mvu.models.StateCmdData
import com.darkos.mvu.validation.ValidationReducer
import com.darkos.mvu.validation.model.mvu.ValidationMessage

class LoginReducer<State : MVUState, Request : Any> private constructor(
    private val loginEffectCreator: LoginEffectCreator<State, Request>,
    private val statusProcessor: StatusProcessor<State>?,
    private val withoutValidationProcessors: List<WithoutValidationReducer<State>>,
    validation: ValidationReducer<State>?
) : Reducer<State> {

    private val validationProcessor = ValidationProcessor(validation)

    override fun update(state: State, message: Message): StateCmdData<State> {
        return when (message) {
            is ValidationMessage -> validationProcessor.processValidationMessage(state, message)
            is LoginMessage -> processLoginMessage(state, message)
            else -> {
                processFieldChangeMessage(state, message)
                    ?: validationProcessor.processOtherMessage(state, message)
                    ?: throw IllegalArgumentException()
            }
        }
    }

    private fun processFieldChangeMessage(state: State, message: Message): StateCmdData<State>? {
        return withoutValidationProcessors.firstOrNull {
            it.valueChangedMessage.isInstance(message)
        }?.let {
            it.update(
                state = WithoutValidationField(""),
                message = message
            ).map { field ->
                it.mapTo(state, field)
            }
        }
    }

    private fun processProgressState(state: State, value: Boolean): State {
        return statusProcessor?.onStateChanged?.let {
            it(state, value)
        } ?: state
    }

    inner class ValidationProcessor(
        private val validation: ValidationReducer<State>?
    ) {
        fun processValidationMessage(
            state: State,
            message: ValidationMessage
        ): StateCmdData<State> {
            return when (message) {
                is ValidationMessage.Success -> {
                    createProgressState(state)
                }
                is ValidationMessage.Error -> {
                    processValidationError(state, message)
                }
                else -> throw IllegalArgumentException()
            }
        }

        private fun processValidationError(
            state: State,
            message: ValidationMessage
        ): StateCmdData<State> {
            return validation?.let { validation ->
                validation.update(
                    state = validation.map(state),
                    message = message
                ).map {
                    validation.mapper(state, it)
                }.map { newState ->
                    statusProcessor?.onStateChanged?.let {
                        it(newState, false)
                    } ?: state
                }
            } ?: throw IllegalArgumentException()
        }

        fun processOtherMessage(
            state: State,
            message: Message
        ): StateCmdData<State>? {
            return validation?.let { validation ->
                validation.update(
                    state = validation.map(state),
                    message = message
                ).map {
                    validation.mapper(state, it)
                }
            }
        }

        fun processValidationClick(state: State): StateCmdData<State>? {
            return validation?.let { validation ->
                validation.update(
                    state = validation.map(state),
                    message = ValidationMessage.ValidationClick
                ).map {
                    processProgressState(validation.mapper(state, it), true)
                }
            }
        }
    }

    private fun createProgressState(state: State): StateCmdData<State> {
        return StateCmdData(
            state = processProgressState(state, true),
            effect = loginEffectCreator.create(state)
        )
    }

    private fun processLoginMessage(
        state: State,
        message: LoginMessage
    ): StateCmdData<State> {
        return when (message) {
            is LoginMessage.LoginClick -> {
                validationProcessor.processValidationClick(state) ?: run {
                    createProgressState(state)
                }
            }
            is LoginMessage.LoginSuccess -> {
                StateCmdData(
                    state = processProgressState(state, false),
                    effect = statusProcessor?.onSuccess?.invoke() ?: None()
                )
            }
            is LoginMessage.LoginFailed -> {
                StateCmdData(
                    state = processProgressState(state, false),
                    effect = statusProcessor?.onFailed?.invoke(message.e) ?: None()
                )
            }
            else -> throw IllegalArgumentException()
        }
    }

    class Builder<State : MVUState, Request : Any> {
        private var statusProcessor: StatusProcessor<State>? = null
        private var withoutValidationProcessors: List<WithoutValidationReducer<State>> = emptyList()
        private var validation: ValidationReducer<State>? = null
        private var loginEffectCreator: LoginEffectCreator<State, Request>? = null

        fun ProcessStatus(block: StatusProcessor.Builder<State>.() -> Unit) {
            statusProcessor = StatusProcessor.Builder<State>().apply(block).build()
        }

        fun WithoutValidation(block: WithoutValidationReducer.Builder<State>.() -> Unit) {
            withoutValidationProcessors =
                WithoutValidationReducer.Builder<State>().apply(block).build()
        }

        fun WithValidation(block: ValidationReducer.Builder<State>.() -> Unit) {
            validation = ValidationReducer.Builder<State>().apply(block).build()
        }

        fun MapStateToLoginRequest(block: (State) -> Request) {
            loginEffectCreator = LoginEffectCreator(block)
        }

        fun build() = LoginReducer(
            loginEffectCreator = loginEffectCreator!!,
            statusProcessor = statusProcessor,
            withoutValidationProcessors = withoutValidationProcessors,
            validation = validation
        )
    }
}



