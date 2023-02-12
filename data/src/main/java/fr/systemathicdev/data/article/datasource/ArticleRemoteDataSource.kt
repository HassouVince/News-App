package fr.systemathicdev.data.article.datasource

import fr.systemathicdev.domain.models.Article

interface ArticleRemoteDataSource {
    suspend fun getTopHeadlinesArticlesByLanguage(apiKey: String): List<Article>
    suspend fun getTopHeadlinesArticlesByCountry(apiKey: String): List<Article>
}