package com.jaiswal.memorygame.viewModels

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jaiswal.memorygame.ImageRemoteRepository
import com.jaiswal.memorygame.models.*
import com.jaiswal.memorygame.models.data.CellData
import com.jaiswal.memorygame.models.response.ImagesResponse

class MainActivityViewModel(application: Application) : AndroidViewModel(application), Interactor {

    private val engine = MemoryEngine()
    private lateinit var cells: MutableList<GridCell>
    private val displayWinMessage: ObservableBoolean = ObservableBoolean(false)
    private var images: MutableLiveData<ImagesResponse> = MutableLiveData()
    private var loadComplete: ObservableBoolean = ObservableBoolean(false)
    private var onLoading: ObservableBoolean = ObservableBoolean(false)

    override fun onCellClicked(cell: GridCell, view: View) {
        engine.cellSelected(cell)
    }

    override fun onGameWon() {
        displayWinMessage.set(true)
    }

    fun getDisplayWinMessage(): ObservableBoolean {
        return displayWinMessage
    }

    fun getLoadComplete():ObservableBoolean{
        return loadComplete
    }

    fun setLoadComplete(flag: Boolean){
        loadComplete.set(flag)
    }

    fun getImageLiveData():MutableLiveData<ImagesResponse>{
        return images
    }

    fun getOnLoading(): ObservableBoolean{
        return onLoading
    }

    fun setOnLoaded(){
        onLoading.set(false)
        setLoadComplete(true)
    }

    fun loadImagesFromRepository(){
        images = ImageRemoteRepository().getImages(images)
    }

    fun onResetClicked(view: View) {
        resetCells()
        displayWinMessage.set(false)
    }

    fun onPlayClicked(view: View){
        onLoading.set(true)
        loadImagesFromRepository()
    }

    private fun resetCells() {
        for (cell in cells) {
            if (cell.getCurrentState().get() == CurrentState.VISIBLE || cell.getCurrentState().get() == CurrentState.DONE) {
                cell.setCurrentState(CurrentState.HIDDEN)
                cell.setDoCloseCell(true)
            }
        }
    }

    fun getGridCells(): List<GridCell> {
        cells = ArrayList()
        val imageList = images.value!!.images
        cells.add(GridCell(
            CellData(
                imageList!![0].getFinalImage(),
                ImageType.TYPE_A
            ), this))
        cells.add(GridCell(CellData(imageList[1].getFinalImage(), ImageType.TYPE_B), this))
        cells.add(GridCell(CellData(imageList[0].getFinalImage(), ImageType.TYPE_A), this))
        cells.add(GridCell(CellData(imageList[1].getFinalImage(), ImageType.TYPE_B), this))
        cells.add(GridCell(CellData(imageList[2].getFinalImage(), ImageType.TYPE_C), this))
        cells.add(GridCell(CellData(imageList[3].getFinalImage(), ImageType.TYPE_D), this))
        cells.add(GridCell(CellData(imageList[2].getFinalImage(), ImageType.TYPE_C), this))
        cells.add(GridCell(CellData(imageList[3].getFinalImage(), ImageType.TYPE_D), this))

        cells.add(GridCell(CellData(imageList[4].getFinalImage(), ImageType.TYPE_E), this))
        cells.add(GridCell(CellData(imageList[5].getFinalImage(), ImageType.TYPE_F), this))
        cells.add(GridCell(CellData(imageList[4].getFinalImage(), ImageType.TYPE_E), this))
        cells.add(GridCell(CellData(imageList[5].getFinalImage(), ImageType.TYPE_F), this))
        cells.add(GridCell(CellData(imageList[6].getFinalImage(), ImageType.TYPE_G), this))
        cells.add(GridCell(CellData(imageList[7].getFinalImage(), ImageType.TYPE_H), this))
        cells.add(GridCell(CellData(imageList[6].getFinalImage(), ImageType.TYPE_G), this))
        cells.add(GridCell(CellData(imageList[7].getFinalImage(), ImageType.TYPE_H), this))
        return cells
    }


}