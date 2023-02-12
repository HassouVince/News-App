package fr.systemathicdev.domain.usecases

import fr.systemathicdev.domain.repositories.ArticleRepository

class GetArticlesByLanguageUseCase(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(apiKey: String) =
        articleRepository.getArticlesByLanguage(apiKey)
}