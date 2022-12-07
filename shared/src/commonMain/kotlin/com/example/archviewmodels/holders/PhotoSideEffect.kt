package com.example.archviewmodels.holders

import io.github.fededri.arch.interfaces.SideEffectInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

sealed class PhotoSideEffect(
    //Use IO dispatcher to run side effects
    override val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    //use viewModelScope
    override val coroutineScope: CoroutineScope? = null
) : SideEffectInterface
