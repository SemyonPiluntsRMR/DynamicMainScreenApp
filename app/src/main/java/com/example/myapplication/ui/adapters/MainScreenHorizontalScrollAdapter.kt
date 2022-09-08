package com.example.myapplication.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemActivityMainScreenBinding
import com.example.myapplication.entities.Category
import com.example.myapplication.helpers.toDp
import com.example.myapplication.helpers.toPx
import com.google.android.flexbox.FlexboxLayoutManager

internal class MainScreenHorizontalScrollAdapter() :
    RecyclerView.Adapter<MainScreenHorizontalViewHolder>() {
    private val categoryList = mutableListOf<Category>()
    private var coloumnWidth = 0

    fun setData(categories: List<Category>, coloumnWidth: Int) {
        categoryList.clear()
        this.coloumnWidth = coloumnWidth
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainScreenHorizontalViewHolder {
        val binding: ItemActivityMainScreenBinding =
            ItemActivityMainScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MainScreenHorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainScreenHorizontalViewHolder, position: Int) {
        holder.bindTo(
            categoryList[position],
            coloumnWidth = coloumnWidth
        )
    }

    override fun getItemCount() = categoryList.size
}

internal class MainScreenHorizontalViewHolder(private val binding: ItemActivityMainScreenBinding) :
    RecyclerView.ViewHolder(binding.root) {

    internal fun bindTo(category: Category, coloumnWidth: Int) {
        val layoutParams = binding.itemActivityMainBtn.layoutParams
        layoutParams.width = coloumnWidth

        binding.itemActivityMainBtn.apply {
            text = category.title
            setBackgroundColor(Color.parseColor(category.color))
        }
    }
}


