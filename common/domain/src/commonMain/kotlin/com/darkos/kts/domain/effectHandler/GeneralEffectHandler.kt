package com.darkos.kts.domain.effectHandler

//import com.company.projectName.domain.common.MessageProcessor
//import com.company.projectName.domain.model.mvu.general.GeneralEffect
//import com.company.projectName.domain.model.mvu.general.GeneralMessage
//import com.darkos.mvu.EffectHandler
//import com.darkos.mvu.models.Effect
//import com.darkos.mvu.models.Idle
//import com.darkos.mvu.models.Message
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.withContext
//
//internal class GeneralEffectHandler(
//    private val messageProcessor: MessageProcessor
//) : EffectHandler {
//
//    override suspend fun call(effect: Effect): Message {
//        check(effect is GeneralEffect) {
//            "this effect handler allow only GeneralEffect"
//        }
//
//        return when (effect) {
//            is GeneralEffect.Timer -> {
//                delay(effect.delay)
//                GeneralMessage.TimerFinished
//            }
//            is GeneralEffect.ShowUserMessage -> {
//                withContext(Dispatchers.Main){
//                    messageProcessor.showMessage(effect.message)
//                }
//                Idle()
//            }
//        }
//    }
//}