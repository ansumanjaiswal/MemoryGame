package com.jaiswal.memorygame.models

import android.view.View

interface Interactor {

    fun onCellClicked(cell: GridCell, view: View)
}