package com.company.projectName.login

import com.company.projectName.login.model.*
import com.darkos.mvu.Reducer
import com.darkos.mvu.map
import com.darkos.mvu.models.*
import com.darkos.mvu.validation.model.Field
import com.darkos.mvu.validation.model.FieldValidationStatus
import com.darkos.mvu.validation.model.mvu.ValidationEffect
import com.darkos.mvu.validation.model.mvu.ValidationMessage
import kotlin.reflect.KClass

class LoginReducer<State : MVUState, Request : Any> private constructor(
    private val loginEffectCreator: LoginEffectCreator<State, Request>,
    private val statusProcessor: StatusProcessor<State>?,
    private val withoutValidationProcessors: List<WithoutValidationReducer<State>>,
    private val validation: ValidationReducer<State>?
) : Reducer<State> {

    override fun update(state: State, message: Message): StateCmdData<State> {
        withoutValidationProcessors.firstOrNull {
            it.valueChangedMessage.isInstance(message)
        }?.let { reducer ->
            return reducer.update(
                state = WithoutValidationField(""),
                message = message
            ).map {
                reducer.mapTo(state, it)
            }
        }

        return when (message) {
            is LoginMessage.LoginClick -> {
                validation?.let { validation ->
                    validation.update(
                        state = validation.map(state),
                        message = ValidationMessage.ValidationClick
                    ).map {
                        validation.mapper(state, it).let {
                            statusProcessor?.onStateChanged?.let {
                                it(state, true)
                            } ?: state
                        }
                    }
                } ?: run {
                    StateCmdData(
                        state = statusProcessor?.onStateChanged?.let {
                            it(state, true)
                        } ?: state,
                        effect = loginEffectCreator.create(state)
                    )
                }
            }
            is ValidationMessage.Success -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, true)
                    } ?: state,
                    effect = loginEffectCreator.create(state)
                )
            }
            is ValidationMessage.Error -> {
                validation?.let { validation ->
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
            is LoginMessage.LoginSuccess -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, false)
                    } ?: state,
                    effect = statusProcessor?.onSuccess?.invoke() ?: None()
                )
            }
            is LoginMessage.LoginFailed -> {
                StateCmdData(
                    state = statusProcessor?.onStateChanged?.let {
                        it(state, false)
                    } ?: state,
                    effect = statusProcessor?.onFailed?.invoke(message.e) ?: None()
                )
            }
            else -> {
                validation?.let { validation ->
                    validation.update(
                        state = validation.map(state),
                        message = message
                    ).map {
                        validation.mapper(state, it)
                    }
                } ?: throw IllegalArgumentException()
            }
        }
    }

    class LoginEffectCreator<State : MVUState, Request : Any>(
        map: (State) -> Request
    ) {
        val create: (State) -> LoginEffect.Login<Request> = {
            map(it).let {
                LoginEffect.Login(it)
            }
        }
    }

    internal class StatusProcessor<T : MVUState>(
        val onStateChanged: ((T, Boolean) -> T)?,
        val onSuccess: (() -> Effect)?,
        val onFailed: ((Exception) -> Effect)?
    )

    class StatusProcessorBuilder<T : MVUState> {
        private var onStateChanged: ((T, Boolean) -> T)? = null
        private var onSuccess: (() -> Effect)? = null
        private var onFailed: ((Exception) -> Effect)? = null

        fun OnStateChanged(block: (T, Boolean) -> T) {
            onStateChanged = block
        }

        fun OnSuccess(block: () -> Effect) {
            onSuccess = block
        }

        fun OnFailed(block: (Exception) -> Effect) {
            onFailed = block
        }

        internal fun build(): StatusProcessor<T>? = StatusProcessor(
            onStateChanged,
            onSuccess,
            onFailed
        )
    }

    class WithoutValidationReducer<T : MVUState>(
        val valueChangedMessage: KClass<out FieldValueChanged>,
        val mapTo: (T, WithoutValidationField) -> T
    ) : Reducer<WithoutValidationField> {
        override fun update(
            state: WithoutValidationField,
            message: Message
        ): StateCmdData<WithoutValidationField> {
            return if (valueChangedMessage.isInstance(message)) {
                StateCmdData(
                    state = state.copy(value = (message as FieldValueChanged).newValue),
                    effect = None()
                )
            } else {
                throw IllegalArgumentException()
            }
        }
    }

    class WithoutValidationBuilder<T : MVUState> {
        private var reducers: List<WithoutValidationReducer<T>> = emptyList()

        fun registerField(
            valueChangedMessage: KClass<out FieldValueChanged>,
            mapTo: (T, WithoutValidationField) -> T
        ) {
            reducers = reducers + WithoutValidationReducer(
                valueChangedMessage = valueChangedMessage,
                mapTo = mapTo
            )
        }

        internal fun build() = reducers
    }

    class Builder<State : MVUState, Request : Any> {
        private var statusProcessor: StatusProcessor<State>? = null
        private var withoutValidationProcessors: List<WithoutValidationReducer<State>> = emptyList()
        private var validation: ValidationReducer<State>? = null
        private var loginEffectCreator: LoginEffectCreator<State, Request>? = null

        fun ProcessStatus(block: StatusProcessorBuilder<State>.() -> Unit) {
            statusProcessor = StatusProcessorBuilder<State>().apply(block).build()
        }

        fun WithoutValidation(block: WithoutValidationBuilder<State>.() -> Unit) {
            withoutValidationProcessors = WithoutValidationBuilder<State>().apply(block).build()
        }

        fun WithValidation(block: ValidationBuilder<State>.() -> Unit) {
            validation = ValidationBuilder<State>().apply(block).build()
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



