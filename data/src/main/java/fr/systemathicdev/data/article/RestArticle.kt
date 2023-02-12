package fr.systemathicdev.data.article

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import fr.systemathicdev.domain.models.Article

@Keep
data class RestArticleBody (
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var articles: List<RestArticle>,
)

@Keep
data class RestArticle (
    @SerializedName("source")
    var source: RestSource?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("content")
    var content: String?,
){
    fun toArticle() = Article(
        title = title ?: "Titre manquant",
        description = description ?: "Description manquante",
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        sourceName = source?.name.orEmpty()
    )
}

@Keep
data class RestSource (
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
)