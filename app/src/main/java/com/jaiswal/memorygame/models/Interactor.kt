package com.jaiswal.memorygame.models

interface Interactor {

    fun onCellClicked(cell: GridCell)
    fun onGameWon()
}