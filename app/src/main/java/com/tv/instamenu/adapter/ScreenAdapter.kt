package com.tv.instamenu.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tv.instamenu.R
import com.tv.instamenu.data.modal.ScreenItem
import com.tv.instamenu.databinding.RowScreenBinding
import com.tv.instamenu.utils.GlideApp


class ScreenAdapter(
    private val mContext: Context,
    private var list: MutableList<ScreenItem> = mutableListOf(),

    private val listener: OnItemSelected,
) : RecyclerView.Adapter<ScreenAdapter.ItemHolder>() {

    lateinit var binding: RowScreenBinding

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        binding = RowScreenBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false
        )
        binding.root.isFocusable = true
        binding.root.isFocusableInTouchMode = true

        return ItemHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val data = list[position]
        holder.bindData(mContext, data, listener)

    }



    fun addItems(newList: MutableList<ScreenItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }

    interface OnItemSelected {
        fun completeOrderClick(position: Int, data: ScreenItem, applyTime: Int, action: String)
    }

    class ItemHolder(containerView: RowScreenBinding) :
        RecyclerView.ViewHolder(containerView.root) {
        private val binding = containerView


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindData(
            context: Context,
            data: ScreenItem,
            listener: OnItemSelected
        ) {


            binding.txtScreen.text = data.title
            GlideApp.with(context)
                .load(data.img)
                .error(R.drawable.logo)
                .placeholder(R.drawable.logo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.image)
              itemView.setOnClickListener {
                  listener.completeOrderClick(bindingAdapterPosition, data ,0 , "")
              }

            itemView.setOnFocusChangeListener { view, isFocused ->
                // add focus handling logic
                if(isFocused) {
                } else {
                }
            }

        }
    }


}