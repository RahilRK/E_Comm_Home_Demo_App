package com.example.e_comm_home_demo_app.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class CommonRecyclerViewAdapter<T, D>(
    val mContext: Context,
    private var mArrayList: ArrayList<T>?,
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract val layoutResId: Int

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    abstract fun onBindData(model: T, position: Int, dataBinding: D)

    abstract fun onItemClick(model: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutResId,
            parent,
            false
        )
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindData(
            mArrayList!![position], holder.adapterPosition,
            dataBinding = (holder as CommonRecyclerViewAdapter<*, *>.ItemViewHolder).mBinding as D
        )

        (holder.mBinding as ViewDataBinding).root.setOnClickListener {
            onItemClick(
                mArrayList!![position],
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return mArrayList!!.size
    }

    internal inner class ItemViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var mBinding: D = binding as D
    }
}
