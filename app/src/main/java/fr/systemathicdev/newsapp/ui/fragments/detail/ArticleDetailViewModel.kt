package fr.systemathicdev.newsapp.ui.fragments.detail

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.domain.usecases.OpenUrlUseCase
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val openUrlUseCase: OpenUrlUseCase
): ViewModel() {
    private var _article: MutableLiveData<AppResult<Article>> =
        MutableLiveData<AppResult<Article>>()

    val article: LiveData<AppResult<Article>>
        get() = _article

    fun updateArticle(article: Article){
        _article.postValue(
            AppResult.Success(article)
        )
    }

    fun openUrl(context: Context, url: String){
        viewModelScope.launch {
            openUrlUseCase.invoke(context, url)
        }
    }
}