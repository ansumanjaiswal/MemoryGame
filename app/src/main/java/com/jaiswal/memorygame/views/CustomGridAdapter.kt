package com.jaiswal.memorygame.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jaiswal.memorygame.models.GridCell
import androidx.databinding.DataBindingUtil.inflate
import com.jaiswal.memorygame.R
import com.jaiswal.memorygame.databinding.ImageCellBinding

class CustomGridAdapter(private var cells: List<GridCell>) : BaseAdapter(){

    override fun getItem(p0: Int): Any {
        return cells[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return cells.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var viewHolder: GridViewHolder
        var itemBinding: ImageCellBinding?
        if (convertView == null) {
            itemBinding = inflate(
                LayoutInflater.from(parent.context),
                R.layout.image_cell,
                parent,
                false
            )
            viewHolder = GridViewHolder(itemBinding)
            viewHolder.view.tag = viewHolder
            itemBinding.executePendingBindings()
        }else{
            viewHolder = convertView.tag as GridViewHolder
        }
        viewHolder.binding.handler = cells[position]
        return viewHolder.binding.root
    }

    private class GridViewHolder internal constructor(internal val binding: ImageCellBinding) {
        internal val view: View = binding.root

    }

}