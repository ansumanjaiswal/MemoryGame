package com.jaiswal.memorygame.models

import com.jaiswal.memorygame.models.data.CellData
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
        val dataOne = spy(CellData("", ImageType.TYPE_A))
        val dataTwo = spy(CellData("", ImageType.TYPE_B))
        cellOne = spy(GridCell(dataOne, interactor))
        cellTwo = spy(GridCell(dataTwo, interactor))
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
        assertNull(engine.getSecondCell())
    }

    @Test
    fun whenSecondCellSelectedOfSameType_CellsAreSetToDone(){
        cellTwo = spy(GridCell(CellData("", ImageType.TYPE_A), interactor))

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