package com.som3a.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding



/**
 * This class is a base for ViewHolder.
 * here we handle bind all views for the adapter items
 * */
abstract class BaseViewHolder<M, WB : ViewBinding> constructor(viewBinding: WB) :
    RecyclerView.ViewHolder(viewBinding.root) {

    private var item: M? = null

    fun doBindings(data: M?) {
        this.item = data
    }

    abstract fun bind()

    fun getRowItem(): M? {
        return item
    }
}