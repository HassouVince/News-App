package fr.systemathicdev.domain.repositories

import android.content.Context
import android.net.Uri
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticlesByLanguage(apiKey: String): Flow<AppResult<List<Article>>>
    suspend fun getArticlesByCountry(apiKey: String): Flow<AppResult<List<Article>>>
    suspend fun openWebIntent(context: Context, url: String)
}