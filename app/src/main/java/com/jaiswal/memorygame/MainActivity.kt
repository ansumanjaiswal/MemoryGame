package com.jaiswal.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gridView: GridView = findViewById(R.id.gl_gameView)
        val cells: MutableList<GridCell> = ArrayList()
        val engine = MemoryEngine()
        cells.add(GridCell("", ImageType.TYPE_A, engine))
        cells.add(GridCell("", ImageType.TYPE_B, engine))
        cells.add(GridCell("", ImageType.TYPE_A, engine))
        cells.add(GridCell("", ImageType.TYPE_B, engine))
        cells.add(GridCell("", ImageType.TYPE_C, engine))
        cells.add(GridCell("", ImageType.TYPE_D, engine))
        cells.add(GridCell("", ImageType.TYPE_C, engine))
        cells.add(GridCell("", ImageType.TYPE_D, engine))
        cells.add(GridCell("", ImageType.TYPE_E, engine))
        cells.add(GridCell("", ImageType.TYPE_F, engine))
        cells.add(GridCell("", ImageType.TYPE_E, engine))
        cells.add(GridCell("", ImageType.TYPE_F, engine))
        cells.add(GridCell("", ImageType.TYPE_G, engine))
        cells.add(GridCell("", ImageType.TYPE_H, engine))
        cells.add(GridCell("", ImageType.TYPE_G, engine))
        cells.add(GridCell("", ImageType.TYPE_H, engine))

        val adapter = CustomeGridAdapter(this, cells)
        gridView.adapter = adapter
    }
}
