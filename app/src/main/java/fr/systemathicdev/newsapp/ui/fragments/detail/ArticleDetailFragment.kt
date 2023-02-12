package fr.systemathicdev.newsapp.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import fr.systemathicdev.commons.extensions.*
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.commons.views.BackFragment
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.newsapp.databinding.FragmentArticleDetailBinding
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent

class ArticleDetailFragment: BackFragment(), KoinComponent {

    private val detailViewModel: ArticleDetailViewModel by inject()

    private val binding get() = _binding!! as FragmentArticleDetailBinding

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentArticleDetailBinding
        .inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.apply {
            article.observe(viewLifecycleOwner, onArticle)
            updateArticle(args.article)
        }
    }

    private val onArticle = Observer<AppResult<Article>> { result ->
        result
            .onSuccess {
                hideLoader()
                displayArticle(it)
            }
            .onError { handleError(it) }
            .onLoading { showLoader() }
    }

    private fun displayArticle(article: Article){
        binding.apply {
            this.article = article
            articleImage.setImageUrl(article.urlToImage)
            urlText.setOnClickListener {
                openUrl(article.url)
            }
        }
    }

    private fun openUrl(url: String){
        detailViewModel.openUrl(
            binding.root.context,
            url
        )
    }

    private fun showLoader(){
        binding.progress.setVisibility(true)
    }

    private fun hideLoader(){
        binding.progress.setVisibility(false)
    }

    private fun handleError(t: Throwable){
        binding.root.context.makeToast(t.getMessageNotNull())
    }
}