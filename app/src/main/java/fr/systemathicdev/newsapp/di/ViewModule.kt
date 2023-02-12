package fr.systemathicdev.newsapp.di

import fr.systemathicdev.newsapp.ui.fragments.articles.ArticlesViewModel
import fr.systemathicdev.newsapp.ui.fragments.detail.ArticleDetailViewModel
import org.koin.dsl.module.module

val viewModule = module(override = true) {
    factory { ArticlesViewModel(get(), get()) }
    factory { ArticleDetailViewModel(get()) }
}