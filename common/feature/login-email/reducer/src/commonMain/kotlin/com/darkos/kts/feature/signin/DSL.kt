package com.darkos.kts.feature.signin

fun LoginByEmailReducer(block: ReducerBuilder.()->Unit) = ReducerBuilder().apply(block).build()