package fr.systemathicdev.domain.usecases

import android.content.Context
import fr.systemathicdev.domain.repositories.ArticleRepository

class OpenUrlUseCase(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(context: Context, url: String) =
        articleRepository.openWebIntent(context, url)
}