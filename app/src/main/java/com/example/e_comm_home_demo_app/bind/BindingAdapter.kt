package com.example.e_comm_home_demo_app.bind

import android.graphics.Paint
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.e_comm_home_demo_app.R
import com.google.android.material.textview.MaterialTextView

class BindingAdapter {

    companion object {

        val TAG = "BindingAdapter"


        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun ImageView.loadImageFromUrl(url: String) {

            Glide.with(this.context).load(url).into(this)
        }

        @BindingAdapter("android:setStrikeThrough")
        @JvmStatic
        fun setStrikeThrough(txtView: MaterialTextView, strikeThrough: Boolean) {
            if (strikeThrough) {
                txtView.paintFlags = txtView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                txtView.paintFlags = txtView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        @BindingAdapter("android:setPriceLabelColor")
        @JvmStatic
        fun setPriceLabelColor(txtView: MaterialTextView, inputText: String) {
            when (inputText) {
                "true" -> {
                    txtView.setTextColor(
                        ContextCompat.getColor(
                            txtView.context,
                            R.color.dark_blue
                        )
                    )
                }
                "false" -> {
                    txtView.setTextColor(
                        ContextCompat.getColor(
                            txtView.context,
                            R.color.red
                        )
                    )
                }
            }
        }
    }
}