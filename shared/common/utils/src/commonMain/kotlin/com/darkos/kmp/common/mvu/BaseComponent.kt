package com.darkos.kmp.common.mvu

import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState

interface BaseComponent<T : MVUState> : ProgramComponent<T>