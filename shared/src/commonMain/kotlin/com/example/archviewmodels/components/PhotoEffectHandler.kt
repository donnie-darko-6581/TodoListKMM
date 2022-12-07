package com.example.archviewmodels.components

import com.example.archviewmodels.holders.PhotoAction
import com.example.archviewmodels.holders.PhotoSideEffect
import io.github.fededri.arch.interfaces.Processor
import io.github.fededri.arch.interfaces.SideEffectInterface

class PhotoEffectHandler : Processor<PhotoSideEffect, PhotoAction> {
    override suspend fun dispatchSideEffect(effect: PhotoSideEffect): PhotoAction {
        TODO("Not yet implemented")
    }

}
