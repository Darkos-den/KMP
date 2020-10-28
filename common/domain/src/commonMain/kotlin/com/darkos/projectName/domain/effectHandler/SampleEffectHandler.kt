package com.darkos.projectName.domain.effectHandler

import com.darkos.projectName.domain.model.mvu.sample.SampleEffect
import com.darkos.projectName.domain.model.mvu.sample.SampleMessage
import com.darkos.projectName.entity.models.common.RemoteResult
import com.darkos.projectName.entity.source.remote.ISampleRemoteRepository
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

class SampleEffectHandler(
    private val remote: ISampleRemoteRepository
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        check(effect is SampleEffect) {
            "this effect handler allow only SampleEffect"
        }

        return when (effect) {
            is SampleEffect.LoadContent -> {
                remote.loadData().let {
                    when (it) {
                        is RemoteResult.Success -> {
                            SampleMessage.SampleResultSuccess(it.data)
                        }
                        is RemoteResult.Error -> {
                            SampleMessage.LoadError(it.error.message.orEmpty())
                        }
                    }
                }
            }
        }
    }
}