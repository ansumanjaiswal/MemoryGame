package com.jaiswal.memorygame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class CustomeGridAdapter(private var context: Context,
                         private var cells: List<GridCell>) : BaseAdapter(){

    private var inflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = inflater!!.inflate(R.layout.image_cell, parent, false)
        }
        convertView?.setOnClickListener(View.OnClickListener {
            /*val animation = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 180f)
            //val animation = ObjectAnimator.ofFloat(view, "rotationY", 180f, 0.0f)
            animation.duration = 2400
            //animation.repeatCount = ObjectAnimator.INFINITE
            animation.repeatCount = 0
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()*/
            cells[position].onCellClicked(it)
        })
        return convertView!!
    }

    override fun getItem(p0: Int): Any {
        return cells[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return cells.size
    }

}