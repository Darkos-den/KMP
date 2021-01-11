package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.model.RestoreState
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.SplashMessage
import com.darkos.kmp.feature.splash.model.SplashState
import com.darkos.mvu.model.ComponentInitialized
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.MVUState
import com.darkos.mvu.model.None
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

internal class SplashReducerTest {

    lateinit var reducer: SplashReducer

    @BeforeEach
    fun init() {
        reducer = SplashReducer()
    }

    @Nested
    inner class WhenComponentInitialize {

        @Test
        fun whenInitState() {
            val result = reducer.update(
                state = SplashState.Init,
                message = ComponentInitialized
            )

            assertEquals(SplashState.PrepareData, result.state)
            assertEquals(SplashEffect.CheckAuthState, result.effect)
        }

        @TestFactory
        fun whenOtherState(): Collection<DynamicTest> {
            val params = listOf(
                SplashState.PrepareData,
                SplashState.RefreshTokenError
            )

            return params.map {
                DynamicTest.dynamicTest("when $it") {
                    val result = reducer.update(
                        state = it,
                        message = ComponentInitialized
                    )

                    assertEquals(it, result.state)
                    assertEquals(None, result.effect)
                }
            }
        }
    }

    @Test
    fun whenNetworkError() {
        val result = reducer.update(
            state = SplashState.Init,
            message = SplashMessage.NetworkError
        )

        assertEquals(SplashState.RefreshTokenError, result.state)
        assertEquals(SplashEffect.ProcessNetworkError, result.effect)
    }

    @Test
    fun whenAppError() {
        val message = "message"
        val result = reducer.update(
            state = SplashState.Init,
            message = SplashMessage.AppError(message)
        )

        assertEquals(SplashState.RefreshTokenError, result.state)
        assertAll(
            "effect",
            { assertTrue { result.effect is SplashEffect.ProcessAppError } },
            { assertEquals(message, (result.effect as SplashEffect.ProcessAppError).message) }
        )
    }

    @TestFactory
    fun whenIdle(): Collection<DynamicTest> {
        val params = listOf(
            SplashState.Init,
            SplashState.RefreshTokenError,
            SplashState.PrepareData
        )

        return params.map {
            DynamicTest.dynamicTest("when state $it") {
                val result = reducer.update(it, Idle)

                assertEquals(it, result.state)
                assertEquals(None, result.effect)
            }
        }
    }

    @Nested
    inner class WhenRestoreState {

        @Test
        fun whenOtherState() {
            val state = object : MVUState() {}

            assertThrows<RuntimeException> {
                reducer.update(
                    SplashState.PrepareData,
                    RestoreState(state)
                )
            }
        }

        @Test
        fun whenRefreshTokenErrorState() {
            val result = reducer.update(
                SplashState.Init,
                RestoreState(SplashState.RefreshTokenError)
            )

            assertEquals(SplashState.PrepareData, result.state)
            assertEquals(SplashEffect.RetryTokenRefresh, result.effect)
        }
    }
}