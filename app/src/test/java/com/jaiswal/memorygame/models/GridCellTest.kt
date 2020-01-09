package com.jaiswal.memorygame.models

import android.animation.ObjectAnimator
import android.widget.ImageView
import com.jaiswal.memorygame.models.data.CellData
import com.jaiswal.memorygame.views.MainActivity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class GridCellTest {


    lateinit var cellOne: GridCell
    var interactor = mock(Interactor::class.java)
    lateinit var activity: MainActivity
    lateinit var animator: ObjectAnimator

    @Before
    fun setup(){
        cellOne = spy(GridCell(CellData("", ImageType.TYPE_A), interactor))
        activity = Robolectric.buildActivity(MainActivity::class.java!!)
            .create()
            .resume()
            .get()
        animator = spy(ObjectAnimator.ofFloat(spy(ImageView(activity)), "rotationY", 0.0f, 90f))
    }

    @Test
    fun shouldSetCurrentStateToDone_whenFinalizeCellCalled(){
        assertEquals(cellOne.getCurrentState().get(), CurrentState.HIDDEN)
        cellOne.finalizeCell(false)
        assertEquals(cellOne.getCurrentState().get(), CurrentState.DONE)
    }

}