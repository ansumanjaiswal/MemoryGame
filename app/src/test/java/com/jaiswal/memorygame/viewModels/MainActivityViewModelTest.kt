package com.jaiswal.memorygame.viewModels

import android.view.View
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class MainActivityViewModelTest {

    lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp(){
        viewModel = spy(MainActivityViewModel(RuntimeEnvironment.application))
    }

    @Test
    fun assertInitialState(){
        assertFalse(viewModel.getOnLoading().get())
        assertFalse(viewModel.getLoadComplete().get())
        assertFalse(viewModel.getDisplayWinMessage().get())
    }

    @Test
    fun messageDisplayed_whenGameWon(){
        viewModel.onGameWon()
        assertTrue(viewModel.getDisplayWinMessage().get())
    }

    @Test
    fun loadingComplete_whenDataLoaded(){
        viewModel.setOnLoaded()
        assertTrue(viewModel.getLoadComplete().get())
        assertFalse(viewModel.getOnLoading().get())
    }

    @Test
    fun startLoading_onPlayClicked(){
        doNothing().`when`(viewModel).loadImagesFromRepository()
        viewModel.onPlayClicked(mock(View::class.java))
        assertTrue(viewModel.getOnLoading().get())
    }

}