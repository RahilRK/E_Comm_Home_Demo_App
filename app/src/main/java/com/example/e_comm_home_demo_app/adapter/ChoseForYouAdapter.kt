package com.example.e_comm_home_demo_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_comm_home_demo_app.R
import com.example.e_comm_home_demo_app.data.model.ChosenForYouDTO
import com.example.e_comm_home_demo_app.databinding.ChosenForYouLargeItemBinding
import com.example.e_comm_home_demo_app.databinding.ChosenForYouSmallItemBinding

class ChoseForYouAdapter(
    context: Context,
    var list: ArrayList<ChosenForYouDTO>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mTAG = "ChoseForYouAdapter"

    companion object {
        const val VIEW_TYPE_LARGE = 1
        const val VIEW_TYPE_SMALL = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_LARGE) {
            val largeItemBinding = DataBindingUtil.inflate<ChosenForYouLargeItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.chosen_for_you_large_item, parent, false
            )

            return LargeViewHolder(largeItemBinding)
        }
        val smallItemBinding = DataBindingUtil.inflate<ChosenForYouSmallItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.chosen_for_you_small_item, parent, false
        )

        return SmallViewHolder(smallItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val model = list[position]

        if (model.view_type == VIEW_TYPE_LARGE) {
            (holder as LargeViewHolder).largeItemBinding.model = model
        } else {
            (holder as SmallViewHolder).smallItemBinding.model = model
        }

    }

    inner class LargeViewHolder(var largeItemBinding: ChosenForYouLargeItemBinding) :
        RecyclerView.ViewHolder(largeItemBinding.root)

    inner class SmallViewHolder(var smallItemBinding: ChosenForYouSmallItemBinding) :
        RecyclerView.ViewHolder(smallItemBinding.root)


    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].view_type
    }
}
