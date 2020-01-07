package com.jaiswal.memorygame.viewModels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jaiswal.memorygame.ImageRemoteRepository
import com.jaiswal.memorygame.models.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application), Interactor {

    private val engine = MemoryEngine()
    lateinit var cells: MutableList<GridCell>

    override fun onCellClicked(cell: GridCell, view: View) {
        engine.cellSelected(cell)
    }

    override fun onGameWon() {
        resetCells()
    }

    fun getImageRemoteRepository(): MutableLiveData<Any> {
        return ImageRemoteRepository().getImages()
    }

    fun onResetClicked(view: View){
        resetCells()
    }

    private fun resetCells() {
        for (cell in cells) {
            if (cell.getCurrentState().get() == CurrentState.VISIBLE || cell.getCurrentState().get() == CurrentState.DONE) {
                cell.setCurrentState(CurrentState.HIDDEN)
                cell.setDoCloseCell(true)
            }
        }
    }

    fun getGridCells(): List<GridCell>{
        cells = ArrayList()
        cells.add(GridCell("", ImageType.TYPE_A, this))
        cells.add(GridCell("", ImageType.TYPE_B, this))
        cells.add(GridCell("", ImageType.TYPE_A, this))
        cells.add(GridCell("", ImageType.TYPE_B, this))
        cells.add(GridCell("", ImageType.TYPE_C, this))
        cells.add(GridCell("", ImageType.TYPE_D, this))
        cells.add(GridCell("", ImageType.TYPE_C, this))
        cells.add(GridCell("", ImageType.TYPE_D, this))
        cells.add(GridCell("", ImageType.TYPE_E, this))
        cells.add(GridCell("", ImageType.TYPE_F, this))
        cells.add(GridCell("", ImageType.TYPE_E, this))
        cells.add(GridCell("", ImageType.TYPE_F, this))
        cells.add(GridCell("", ImageType.TYPE_G, this))
        cells.add(GridCell("", ImageType.TYPE_H, this))
        cells.add(GridCell("", ImageType.TYPE_G, this))
        cells.add(GridCell("", ImageType.TYPE_H, this))
        return cells
    }


}