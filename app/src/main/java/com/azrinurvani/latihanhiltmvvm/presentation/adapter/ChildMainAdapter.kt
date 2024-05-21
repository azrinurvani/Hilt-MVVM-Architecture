package com.azrinurvani.latihanhiltmvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azrinurvani.latihanhiltmvvm.R
import com.azrinurvani.latihanhiltmvvm.databinding.ItemFullContentBinding
import com.azrinurvani.latihanhiltmvvm.databinding.ItemHalfContentBinding
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import com.azrinurvani.latihanhiltmvvm.utils.Utils.formatDateTime
import com.azrinurvani.latihanhiltmvvm.utils.Utils.loadImageBitmap

//TODO - Step 32
class ChildMainAdapter(val list : List<News>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var actionClick : ((News)-> Unit)? = null

    fun setActionClick(actionClick:((News)->Unit) ?= null){
        this.actionClick = actionClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER)
            FullViewHolder(
                ItemFullContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else
            HalfViewHolder(
                ItemHalfContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FullViewHolder -> {
                holder.bindItem(list[position])
            }
            is HalfViewHolder -> {
                holder.bindItem(list[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position==0){
            HEADER
        }else{
            OTHER
        }
    }

    inner class FullViewHolder(private val binding : ItemFullContentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(news: News){
            with(binding){
                with(binding) {
                    tvTitle.text = news.title
                    tvDesc.text = news.description
                    tvPublish.text = formatDateTime(news.publishedAt)
                    tvNameSource.text = news.name
                    loadImageBitmap(
                        context = itemView.context,
                        placeHolder = R.drawable.news_placeholder,
                        imageView = imgNews,
                        uri = news.urlToImage
                    )
                    itemView.setOnClickListener {
                        actionClick?.invoke(news)
                    }
                }
            }
        }
    }

    inner class HalfViewHolder(private val binding: ItemHalfContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(news: News) {
            with(binding) {
                tvTitle.text = news.title
                tvDesc.text = news.description
                tvPublish.text = formatDateTime(news.publishedAt)
                tvNameSource.text = news.name
                loadImageBitmap(
                    context = itemView.context,
                    placeHolder = R.drawable.news_placeholder,
                    imageView = ivImageNews,
                    uri = news.urlToImage
                )
                itemView.setOnClickListener {
                    actionClick?.invoke(news)
                }
            }
        }
    }

    companion object {
        private const val HEADER = 0
        private const val OTHER = 1
    }
}