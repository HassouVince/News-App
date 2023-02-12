package fr.systemathicdev.newsapp.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.commons.extensions.setImageUrl
import fr.systemathicdev.commons.extensions.setVisibility
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.newsapp.databinding.ItemArticleBinding

class ArticleViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    interface OnArticleClickListener{
        fun onArticleClick(article: Article)
    }

    constructor(parent: ViewGroup) : this(
        ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: Article,
        onArticleClickListener: OnArticleClickListener
    ){
        binding.apply {
            articleName.text = item.title
            descriptionText.text = item.description
            setExpandableView(item.expanded)
            setArticleImage(item.urlToImage)

            root.setOnClickListener {
                onArticleClickListener.onArticleClick(item)
            }

            icOpen.setOnClickListener {
                item.expanded = !item.expanded
                descriptionText.setVisibility(item.expanded)
                setExpandableView(item.expanded)
            }
        }
    }

    private fun setArticleImage(url: String){
        binding.apply {
            articleImage.setImageUrl(
                url = url,
                onStartCallback = {
                    setProgressBarVisibility(true)
                },
                onErrorCallback = {
                    setProgressBarVisibility(false)
                },
                onSuccessCallback = {
                    setProgressBarVisibility(false)
                }
            )
        }
    }

    private fun setProgressBarVisibility(isLoading: Boolean){
        binding.progressBar.setVisibility(isLoading)
        binding.articleImage.setVisibility(!isLoading)
    }

    private fun setExpandableView(expanded: Boolean) {
        if (expanded)
            binding.icOpen.setImageResource(fr.systemathicdev.commons.R.drawable.ic_up)
        else
            binding.icOpen.setImageResource(fr.systemathicdev.commons.R.drawable.ic_down)
    }
}