package fr.systemathicdev.newsapp.ui.fragments.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.systemathicdev.commons.extensions.*
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.commons.views.BackFragment
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.newsapp.databinding.FragmentArticlesBinding
import fr.systemathicdev.newsapp.BuildConfig
import fr.systemathicdev.newsapp.ui.adapters.ArticlesAdapter
import fr.systemathicdev.newsapp.ui.adapters.viewholders.ArticleViewHolder
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent

class ArticlesFragment:
    BackFragment(),
    KoinComponent,
    ArticleViewHolder.OnArticleClickListener {

    private val viewModel: ArticlesViewModel by inject()

    private val articlesAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter(this)
    }

    private val binding get() = _binding!! as FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentArticlesBinding
        .inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articlesList.apply {
                adapter = articlesAdapter
                layoutManager = LinearLayoutManager(context)
        }

        initSpinner()

        viewModel.apply {
            articles.observe(viewLifecycleOwner, onArticles)
            viewModel.getArticlesByCountry(BuildConfig.API_KEY)
        }
    }

    private val onArticles = Observer<AppResult<List<Article>>> { result ->
        result
            .onSuccess {
                hideLoader()
                articlesAdapter.submitList(it)
                binding.articlesList.scrollToPosition(0)
            }
            .onError { handleError(it) }
            .onLoading { showLoader() }
    }

    override fun onArticleClick(article: Article) {
        goToDetail(article)
    }

    private fun goToDetail(article: Article){
        ArticlesFragmentDirections
            .actionArticlesFragmentToArticleDetailFragment(article)
            .let {
                findNavController().navigateSafe(it)
            }
    }

    private fun initSpinner() {
        binding.articlesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    viewModel.getArticlesByCountry(BuildConfig.API_KEY)
                } else {
                    viewModel.getArticlesByLanguage(BuildConfig.API_KEY)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
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