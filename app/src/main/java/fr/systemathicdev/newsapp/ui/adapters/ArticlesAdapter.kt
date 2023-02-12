package fr.systemathicdev.newsapp.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.newsapp.ui.adapters.viewholders.ArticleViewHolder

class ArticlesAdapter(
    var onArticleClickListener: ArticleViewHolder.OnArticleClickListener
) : RecyclerView.Adapter<ArticleViewHolder>() {

    private var currentList: MutableList<Article> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Article>){
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(parent)

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(currentList[position], onArticleClickListener)
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int  = position
}