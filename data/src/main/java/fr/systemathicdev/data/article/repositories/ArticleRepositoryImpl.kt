package fr.systemathicdev.data.article.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.viewbinding.BuildConfig
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.data.article.datasource.ArticleRemoteDataSource
import fr.systemathicdev.data.utils.IntentFactory
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.domain.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class ArticleRepositoryImpl(
    private val remoteSource: ArticleRemoteDataSource,
    private val intentFactory: IntentFactory,
) : ArticleRepository {

    override suspend fun getArticlesByLanguage(apiKey: String): Flow<AppResult<List<Article>>> =
        withContext(Dispatchers.IO) {
            flowOf(
                try {
                    AppResult.Success(
                        remoteSource.getTopHeadlinesArticlesByLanguage(apiKey)
                    )
                } catch (e: Exception) {
                    AppResult.Error(e)
                }
            )
        }

    override suspend fun getArticlesByCountry(apiKey: String): Flow<AppResult<List<Article>>> =
        withContext(Dispatchers.IO) {
            flowOf(
                try {

                    AppResult.Success(
                        remoteSource.getTopHeadlinesArticlesByCountry(apiKey)
                    )
                } catch (e: Exception) {
                    AppResult.Error(e)
                }
            )
        }

    override suspend fun openWebIntent(context: Context, url: String) =
        withContext(Dispatchers.IO) {
            intentFactory.create(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }.let {
                context.startActivity(Intent.createChooser(it,"Selectionnez une application pour ouvrir cet url"))
            }
        }
}