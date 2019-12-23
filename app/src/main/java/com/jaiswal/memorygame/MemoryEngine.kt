package com.jaiswal.memorygame

class MemoryEngine {
    var firstOpen: GridCell? = null
    var secondOpen : GridCell? = null
    var isSingleSelected = false
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
        firstOpen?.closeCell()
        secondOpen?.closeCell()
    }

    private fun commitAsCorrectSelection() {
        firstOpen?.finalizeCell()
        secondOpen?.finalizeCell()
        /*firstOpen?.setCurrentState(CurrentState.DONE)
        secondOpen?.setCurrentState(CurrentState.DONE)*/
        count += 2
        if (count == 16) {
            //Game won
        }
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

}