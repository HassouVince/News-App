package fr.systemathicdev.domain.usecases

import fr.systemathicdev.domain.repositories.ArticleRepository

class GetArticlesByCountryUseCase (
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(apiKey: String) =
        articleRepository.getArticlesByCountry(apiKey)
}