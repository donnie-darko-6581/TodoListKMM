package com.example.archviewmodels.components

import com.example.archviewmodels.holders.PhotoAction
import com.example.archviewmodels.holders.PhotoEvent
import com.example.archviewmodels.holders.PhotoSideEffect
import com.example.archviewmodels.holders.PhotoState
import io.github.fededri.arch.Next
import io.github.fededri.arch.interfaces.Updater

object PhotoLogic : Updater<PhotoAction, PhotoState, PhotoSideEffect, PhotoEvent> {

    override fun onNewAction(
        action: PhotoAction,
        currentState: PhotoState
    ): Next<PhotoState, PhotoSideEffect, PhotoEvent> {
        TODO("Not yet implemented")
    }

}
