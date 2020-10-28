package com.darkos.kts.signin

fun LoginByEmailReducer(block: ReducerBuilder.()->Unit) = ReducerBuilder().apply(block).build()