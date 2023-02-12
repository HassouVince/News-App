package fr.systemathicdev.data

import fr.systemathicdev.data.article.ArticleApi
import fr.systemathicdev.data.article.RestArticle
import fr.systemathicdev.data.article.RestArticleBody
import fr.systemathicdev.data.article.RestSource
import fr.systemathicdev.data.article.datasource.ArticleRemoteDataSource
import fr.systemathicdev.data.article.datasource.ArticleRemoteDataSourceImpl
import fr.systemathicdev.domain.models.Article
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleRemoteDataSourceTests {

    @Mock
    lateinit var articleApi: ArticleApi

    private lateinit var articleRemoteDataSource: ArticleRemoteDataSource

    @Before
    fun initTests() {
        articleRemoteDataSource = ArticleRemoteDataSourceImpl(
            articleApi,
        )
    }

    @Test
    fun `should return articles by country`() = runBlocking<Unit> {
        `when`(articleApi.getTopHeadlines("apikey", null, "fr"))
            .thenReturn(getRestArticle())

        assertThat(
            articleRemoteDataSource.getTopHeadlinesArticlesByCountry("apikey")
        ).isEqualTo(
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
        )
    }

    @Test
    fun `should return articles by language`() = runBlocking<Unit> {
        `when`(articleApi.getTopHeadlines("apikey", "fr", null))
            .thenReturn(getRestArticle())

        assertThat(
            articleRemoteDataSource.getTopHeadlinesArticlesByLanguage("apikey")
        ).isEqualTo(
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
        )
    }

    private fun getRestArticle() =
        RestArticleBody(
            status = "ok",
            totalResults = 2,
            articles = listOf(
                RestArticle(
                    title = "title",
                    description = "desc",
                    content = "content",
                    author = "auth",
                    url = "url",
                    urlToImage = "imageUrl",
                    publishedAt = "02/01/2023",
                    source = RestSource(
                        id = "sourceId",
                        name = "source",
                    )
                ),
                RestArticle(
                    title = "title2",
                    description = "desc2",
                    content = "content2",
                    author = "auth2",
                    url = "url2",
                    urlToImage = "imageUrl2",
                    publishedAt = "03/01/2023",
                    source = RestSource(
                        id = "sourceId2",
                        name = "source2",
                    )
                ),
            )
        )
}