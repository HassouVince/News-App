package fr.systemathicdev.newsapp.ui.fragments.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.domain.usecases.GetArticlesByCountryUseCase
import fr.systemathicdev.domain.usecases.GetArticlesByLanguageUseCase
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val getArticlesByLanguageUseCase: GetArticlesByLanguageUseCase,
    private val getArticlesByCountryUseCase: GetArticlesByCountryUseCase,
) : ViewModel() {
    private var _articles: MutableLiveData<AppResult<List<Article>>> =
        MutableLiveData<AppResult<List<Article>>>()

    val articles: LiveData<AppResult<List<Article>>>
        get() = _articles

    fun getArticlesByLanguage(apiKey: String){
        viewModelScope.launch {
            _articles.postValue(AppResult.Loading())
            getArticlesByLanguageUseCase(apiKey)
                .collect(_articles::postValue)
        }
    }

    fun getArticlesByCountry(apiKey: String){
        viewModelScope.launch {
            _articles.postValue(AppResult.Loading())
            getArticlesByCountryUseCase(apiKey)
                .collect(_articles::postValue)
        }
    }
}