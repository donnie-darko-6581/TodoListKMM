package com.example.kmmlist.archviewmodels.components

import com.example.archviewmodels.components.PhotoEffectHandler
import com.example.archviewmodels.holders.FetchPhotoListEffect
import com.example.kmmlist.archviewmodels.mocks.PhotoListMock
import com.example.usecases.PhotoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class PhotoEffectHandlerTest {

    private val repo = PhotoListMock()

    private val photoListUseCase = PhotoListUseCase(
        dispatcher = Dispatchers.Default,
        repository = repo
    )

    private val effectHandler = PhotoEffectHandler(
        photosUseCase = photoListUseCase
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test successful response`(): TestResult {
        val effect = FetchPhotoListEffect

        return runTest {
            val action = effectHandler.dispatchSideEffect(effect)
            // find a way to assert that it is success/fail
        }
    }
}