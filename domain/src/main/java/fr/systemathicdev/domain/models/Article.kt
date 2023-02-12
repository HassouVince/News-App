package fr.systemathicdev.domain.models

import java.io.Serializable

data class Article (
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val sourceName: String,
    var expanded: Boolean = false,
): Serializable{
    fun getPublishedAtConverted() =
        try{
            publishedAt.split("T")[0]
        }catch (e: Exception){
            e.printStackTrace()
            publishedAt
        }
}