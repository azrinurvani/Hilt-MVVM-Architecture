package com.azrinurvani.latihanhiltmvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azrinurvani.latihanhiltmvvm.databinding.ItemContentMainBinding
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import com.azrinurvani.latihanhiltmvvm.utils.Utils.listCategory

//TODO - Step 31
class ParentMainAdapter(val list: List<List<News>>) : RecyclerView.Adapter<ParentMainAdapter.ParentViewHolder>(){


    private var actionClick: ((News) -> Unit)? = null

    fun setActionClick(actionClick: ((News) -> Unit)? = null) {
        this.actionClick = actionClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(
            ItemContentMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bindItem(Pair(list[position],listCategory[position]))
    }

    inner class ParentViewHolder(private val binding : ItemContentMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(list: Pair<List<News>,String>){
            binding.tvTitleMain.apply {
                isVisible = list.second != "general"
                text = list.second
            }

            val childAdapter = ChildMainAdapter(list.first)
            childAdapter.setActionClick(
                actionClick = {
                    actionClick?.invoke(it)
                }
            )

            binding.rvHead.apply {
                layoutManager = GridLayoutManager(context,2)
                adapter = childAdapter
                (layoutManager as? GridLayoutManager)?.apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                        override fun getSpanSize(position: Int): Int {
                            return if (position == 0) 2 else 1
                        }

                    }
                }
            }
        }
    }
}