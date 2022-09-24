package com.som3a.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding


/**
 * This class is a base for adapters which hold data to represent it in lists.
 * here we handle all common cases with the adapters created in the code
 * */
abstract class BaseRecyclerAdapter<M : Any, WB : ViewBinding, VH : BaseViewHolder<M, WB>>(callback: DiffUtil.ItemCallback<M>) :
    ListAdapter<M, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings((getItem(position)))
        holder.bind()
    }

    override fun submitList(items: List<M>?) {
        super.submitList(items ?: emptyList())
    }
}