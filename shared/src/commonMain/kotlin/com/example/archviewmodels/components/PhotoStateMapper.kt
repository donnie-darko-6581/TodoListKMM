package com.example.archviewmodels.components

import com.example.archviewmodels.holders.PhotoRenderState
import com.example.archviewmodels.holders.PhotoState
import io.github.fededri.arch.interfaces.StateMapper

object PhotoStateMapper : StateMapper<PhotoState, PhotoRenderState> {
    override fun mapToRenderState(state: PhotoState): PhotoRenderState {
        TODO("Not yet implemented")
    }

}
