package fr.systemathicdev.data.article

import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("language") language: String?,
        @Query("country") country: String?,
    ): RestArticleBody
}