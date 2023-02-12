package fr.systemathicdev.data.article.datasource

import fr.systemathicdev.data.article.ArticleApi
import fr.systemathicdev.domain.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleRemoteDataSourceImpl(
    private val api: ArticleApi
) : ArticleRemoteDataSource {
    override suspend fun getTopHeadlinesArticlesByLanguage(apiKey: String): List<Article> =
        try {
            withContext(Dispatchers.IO) {
                api.getTopHeadlines(apiKey, DEFAULT_LANGUAGE, null)
                    .articles
                    .map { it.toArticle() }
            }
        }catch (e: Exception){
            throw e
        }

    override suspend fun getTopHeadlinesArticlesByCountry(apiKey: String): List<Article>  =
        withContext(Dispatchers.IO) {
            api.getTopHeadlines(apiKey, null, DEFAULT_LANGUAGE)
                .articles
                .map { it.toArticle() }
        }

    companion object{
        private const val DEFAULT_LANGUAGE = "fr"
    }
}