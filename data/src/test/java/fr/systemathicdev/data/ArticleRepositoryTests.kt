package fr.systemathicdev.data

import android.content.Context
import android.content.Intent
import fr.systemathicdev.commons.extensions.getLastErrorOrNull
import fr.systemathicdev.commons.extensions.getLastSuccessDataOrNull
import fr.systemathicdev.commons.tools.AppResult
import fr.systemathicdev.data.article.datasource.ArticleRemoteDataSource
import fr.systemathicdev.data.article.repositories.ArticleRepositoryImpl
import fr.systemathicdev.data.utils.IntentFactory
import fr.systemathicdev.domain.models.Article
import fr.systemathicdev.domain.repositories.ArticleRepository
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleRepositoryTests {

    @Mock
    lateinit var articleRemoteDataSource: ArticleRemoteDataSource

    private lateinit var articleRepository: ArticleRepository

    @Before
    fun initTests() {
        articleRepository = ArticleRepositoryImpl(
            articleRemoteDataSource,
            IntentFactory()
        )
    }

    @Test
    fun `should return articles by country`() = runBlocking<Unit> {
        val articles = getArticles()
        `when`(articleRemoteDataSource.getTopHeadlinesArticlesByCountry(anyString()))
            .thenReturn(articles)

        assertThat(
            articleRepository.getArticlesByCountry("apikey")
                .getLastSuccessDataOrNull()
        ).isEqualTo(articles)
    }

    @Test
    fun `should return articles by language`() = runBlocking<Unit> {
        val articles = getArticles()
        `when`(articleRemoteDataSource.getTopHeadlinesArticlesByLanguage(anyString()))
            .thenReturn(articles)

        assertThat(
            articleRepository.getArticlesByLanguage("apikey")
                .getLastSuccessDataOrNull()
        ).isEqualTo(articles)
    }

    @Test
    fun `should return error when remote source throw exception`() = runBlocking<Unit> {
        given(articleRemoteDataSource.getTopHeadlinesArticlesByLanguage(anyString())).willAnswer {
            throw Exception("")
        }

        assertThat(
            articleRepository.getArticlesByLanguage("apikey")
                .getLastErrorOrNull()
        ).isInstanceOf(AppResult.Error::class.java)
    }

    @Test
    fun `should open url with intent`() = runBlocking<Unit>{
        val context: Context = mock(Context::class.java)
        val factory: IntentFactory = mock(IntentFactory::class.java)
        val intent: Intent = mock(Intent::class.java)
        val repos = ArticleRepositoryImpl(
            articleRemoteDataSource,
            factory
        )

        `when`(factory.create(anyString())).thenReturn(intent)
        repos.openWebIntent(context, "https://youtube.com")
        val factoryCaptor: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        verify(factory).create(
            capture(factoryCaptor)
        )
        assertThat(factoryCaptor.value).isEqualTo(Intent.ACTION_VIEW)
    }

    private fun getArticles() =
        listOf(
            Article(
                title = "title",
                description = "desc",
                url = "url",
                urlToImage = "imageUrl",
                publishedAt = "02/01/2023",
                sourceName = "source",
            ),
            Article(
                title = "title2",
                description = "desc2",
                url = "url2",
                urlToImage = "imageUrl2",
                publishedAt = "03/01/2023",
                sourceName = "source2",
            )
        )

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}