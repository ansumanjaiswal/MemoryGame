package com.jaiswal.memorygame.viewModels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.jaiswal.memorygame.models.GridCell
import com.jaiswal.memorygame.models.ImageType
import com.jaiswal.memorygame.models.Interactor
import com.jaiswal.memorygame.models.MemoryEngine

class MainActivityViewModel(application: Application) : AndroidViewModel(application), Interactor {
    private val engine = MemoryEngine()

    override fun onCellClicked(cell: GridCell, view: View) {
        engine.cellSelected(cell)
    }

    fun getCells(): List<GridCell>{
        val cells: MutableList<GridCell> = ArrayList()
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