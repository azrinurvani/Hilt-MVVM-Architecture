package com.azrinurvani.latihanhiltmvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azrinurvani.latihanhiltmvvm.R
import com.azrinurvani.latihanhiltmvvm.databinding.ItemFullContentBinding
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import com.azrinurvani.latihanhiltmvvm.utils.Utils

//TODO - Step 51
class NewsHistoryAdapter(val list: List<News>) : RecyclerView.Adapter<NewsHistoryAdapter.NewsHistoryViewHolder>() {

    private var actionClick: ((News) -> Unit)? = null

    fun setActionClick(actionClick: ((News) -> Unit)? = null) {
        this.actionClick = actionClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHistoryViewHolder {
        return NewsHistoryViewHolder(
            ItemFullContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NewsHistoryViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    inner class NewsHistoryViewHolder(private val binding : ItemFullContentBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        fun bindItem(news: News) {
            with(binding) {
                tvTitle.text = news.title
                tvDesc.text = news.description
                tvPublish.text = Utils.formatDateTime(news.publishedAt)
                tvNameSource.text = news.name
                Utils.loadImageBitmap(
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