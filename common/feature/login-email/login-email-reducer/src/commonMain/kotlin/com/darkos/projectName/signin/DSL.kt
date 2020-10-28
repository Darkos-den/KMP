package com.darkos.projectName.signin

fun LoginByEmailReducer(block: ReducerBuilder.()->Unit) = ReducerBuilder().apply(block).build()