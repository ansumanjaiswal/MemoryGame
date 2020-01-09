package com.jaiswal.memorygame.models

open class MemoryEngine {
    private var firstOpen: GridCell? = null
    private var secondOpen : GridCell? = null
    private var isSingleSelected = false
    var count = 0

    private fun matchAndEvaluate(){
        if(firstOpen?.getImageType() == secondOpen?.getImageType()){
            commitAsCorrectSelection()
        }else{
            resetCells()
        }

        handleUnmatchedPairs()
    }

    private fun resetCells() {
        firstOpen?.setCurrentState(CurrentState.HIDDEN)
        secondOpen?.setCurrentState(CurrentState.HIDDEN)
        firstOpen?.setDoCloseCell(true)
        secondOpen?.setDoCloseCell(true)
    }

    private fun commitAsCorrectSelection() {
        count += 2
        var gameWon = false
        firstOpen?.finalizeCell(gameWon)
        if (count == 16) {
            //Game won
            count = 0
            gameWon = true
        }
        secondOpen?.finalizeCell(gameWon)

    }

    private fun handleUnmatchedPairs() {
        firstOpen = null
        secondOpen = null
    }

    fun cellSelected(cell: GridCell){
        if(isSingleSelected){
            secondOpen = cell
            matchAndEvaluate()
            isSingleSelected = false
        }else{
            firstOpen = cell
            cell.setCurrentState(CurrentState.VISIBLE)
            isSingleSelected = true
        }
    }

    fun getIsSingleCellSelected(): Boolean{
        return isSingleSelected
    }

    fun getFirstCell(): GridCell?{
        return firstOpen
    }

    fun getSecondCell(): GridCell? {
        return secondOpen
    }
}