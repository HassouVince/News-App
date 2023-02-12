package fr.systemathicdev.newsapp.di

import fr.systemathicdev.data.article.ArticleApi
import fr.systemathicdev.data.article.datasource.ArticleRemoteDataSource
import fr.systemathicdev.data.article.datasource.ArticleRemoteDataSourceImpl
import fr.systemathicdev.data.article.repositories.ArticleRepositoryImpl
import fr.systemathicdev.data.utils.RetrofitClientProvider
import fr.systemathicdev.domain.repositories.ArticleRepository
import fr.systemathicdev.domain.usecases.GetArticlesByCountryUseCase
import fr.systemathicdev.domain.usecases.GetArticlesByLanguageUseCase
import fr.systemathicdev.domain.usecases.OpenUrlUseCase
import org.koin.dsl.module.module
import retrofit2.Retrofit

private fun provideArticleApi(retrofit: Retrofit) = retrofit.create(ArticleApi::class.java)

val repositoryModule = module(override = true) {
    single<ArticleRepository>{ ArticleRepositoryImpl(get(),get()) }
}

val remoteModule = module(override = true) {
    single<ArticleRemoteDataSource>{ ArticleRemoteDataSourceImpl(get()) }
}

val retrofitModule = module(override = true) {
    single<Retrofit>{ RetrofitClientProvider().getRetrofitClient() }

    single<ArticleApi> { provideArticleApi(get()) }
}

val useCaseModule = module(override = true) {
    single<GetArticlesByLanguageUseCase> { GetArticlesByLanguageUseCase(get()) }
    single<GetArticlesByCountryUseCase> { GetArticlesByCountryUseCase(get()) }
    single<OpenUrlUseCase> { OpenUrlUseCase(get()) }
}