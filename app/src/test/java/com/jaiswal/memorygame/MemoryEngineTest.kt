package com.jaiswal.memorygame

import com.jaiswal.memorygame.models.*
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy

class MemoryEngineTest {

    lateinit var engine: MemoryEngine
    lateinit var cellOne: GridCell
    lateinit var cellTwo: GridCell
    var interactor = mock(Interactor::class.java)
    @Before
    fun setup() {
        engine = spy(MemoryEngine())
        cellOne = spy(GridCell("", ImageType.TYPE_A, interactor))
        cellTwo = spy(GridCell("", ImageType.TYPE_B, interactor))
    }

    @Test
    fun isCellSelectedFalse_whenNoCellClicked() {
        assertFalse(engine.getIsSingleCellSelected())
    }

    @Test
    fun whenFirstCellSelected_currentStateIsVisible() {
        assertEquals(cellOne.getCurrentState().get(), CurrentState.HIDDEN)
        assertFalse(engine.getIsSingleCellSelected())
        engine.cellSelected(cellOne)
        assertEquals(cellOne.getCurrentState().get(), CurrentState.VISIBLE)
        assertTrue(engine.getIsSingleCellSelected())
    }

    @Test
    fun whenSecondCellSelectedOfDifferentType_CellsAreHidden(){
        assertEquals(cellOne.getCurrentState().get(), CurrentState.HIDDEN)
        assertEquals(cellTwo.getCurrentState().get(), CurrentState.HIDDEN)
        assertFalse(engine.getIsSingleCellSelected())
        engine.cellSelected(cellOne)
        assertTrue(engine.getIsSingleCellSelected())
        assertEquals(cellOne.getCurrentState().get(), CurrentState.VISIBLE)
        engine.cellSelected(cellTwo)
        assertFalse(engine.getIsSingleCellSelected())
        assertEquals(cellOne.getCurrentState().get(), CurrentState.HIDDEN)
        assertEquals(cellTwo.getCurrentState().get(), CurrentState.HIDDEN)
        assertNull(engine.getFirstCell())
        assertNull(engine.getSecondCell())/*
        verify(cellOne, times(1)).setDoCloseCell(true)
        verify(cellTwo, times(1)).setDoCloseCell(true)*/
    }

    @Test
    fun whenSecondCellSelectedOfSameType_CellsAreSetToDone(){
        cellTwo = spy(GridCell("", ImageType.TYPE_A, interactor))

        assertEquals(cellOne.getCurrentState().get(), CurrentState.HIDDEN)
        assertEquals(cellTwo.getCurrentState().get(), CurrentState.HIDDEN)
        assertFalse(engine.getIsSingleCellSelected())

        engine.cellSelected(cellOne)
        assertTrue(engine.getIsSingleCellSelected())
        assertEquals(cellOne.getCurrentState().get(), CurrentState.VISIBLE)

        engine.cellSelected(cellTwo)
        assertFalse(engine.getIsSingleCellSelected())
        assertEquals(cellOne.getCurrentState().get(), CurrentState.DONE)
        assertEquals(cellTwo.getCurrentState().get(), CurrentState.DONE)
        assertNull(engine.getFirstCell())
        assertNull(engine.getSecondCell())
    }
}