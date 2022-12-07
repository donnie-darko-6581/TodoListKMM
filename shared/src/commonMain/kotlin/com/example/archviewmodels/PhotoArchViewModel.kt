package com.example.archviewmodels

import com.example.archviewmodels.components.PhotoLogic
import com.example.archviewmodels.components.PhotoStateMapper
import com.example.archviewmodels.holders.*
import io.github.fededri.arch.ArchViewModel
import io.github.fededri.arch.ThreadInfo
import io.github.fededri.arch.interfaces.Processor

class PhotoArchViewModel(
    private val threadInfo: ThreadInfo,
    private val processor: Processor<PhotoSideEffect, PhotoAction>
) : ArchViewModel<PhotoAction, PhotoState, PhotoSideEffect, PhotoEvent, PhotoRenderState>(
    updater = PhotoLogic,
    initialState = PhotoState.initialState(),
    threadInfo = threadInfo,
    initialEffects = setOf(),
    processor = processor,
    stateMapper = PhotoStateMapper
) {

}

