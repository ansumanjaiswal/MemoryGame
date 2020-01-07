package com.jaiswal.memorygame

import com.jaiswal.memorygame.models.CurrentState
import com.jaiswal.memorygame.models.GridCell
import com.jaiswal.memorygame.models.ImageType
import com.jaiswal.memorygame.models.Interactor
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


//@RunWith(RobolectricTestRunner::class)
class GridCellTest {


    lateinit var cellOne: GridCell
    var interactor = mock(Interactor::class.java)
    //lateinit var activity: MainActivity
    //lateinit var animator: ObjectAnimator

    @Before
    fun setup(){
        cellOne = Mockito.spy(GridCell("", ImageType.TYPE_A, interactor))
        /*activity = Robolectric.buildActivity(MainActivity::class.java!!)
            .create()
            .resume()
            .get()
        spy(ObjectAnimator.ofFloat(spy(ImageView(activity)), "rotationY", 0.0f, 90f))*/
    }

    /*@Test
    fun shouldAnimate_whenCellClickedAndStateHidden(){
        doReturn(animator).`when`(cellOne).getAnimator(mock(View::class.java))
        cellOne.onViewClicked(mock(View::class.java))
        verify(interactor, times(1)).onCellClicked(cellOne, mock(View::class.java))
    }*/

    @Test
    fun shouldSetCurrentStateToDone_whenFinalizeCellCalled(){
        assertEquals(cellOne.getCurrentState().get(), CurrentState.HIDDEN)
        cellOne.finalizeCell(false)
        assertEquals(cellOne.getCurrentState().get(), CurrentState.DONE)
    }


    /*private fun getYAnimator(`object`: Any): ObjectAnimator {
        val property = "y"
        val startY = 90f
        val endY = 10f
        val yAnimator = ObjectAnimator.ofFloat(`object`, property, startY, endY)
        yAnimator.setDuration(20)
        yAnimator.setRepeatCount(2)
        yAnimator.setInterpolator(AccelerateInterpolator())
        yAnimator.setRepeatMode(ValueAnimator.REVERSE)
        return yAnimator
    }*/
}