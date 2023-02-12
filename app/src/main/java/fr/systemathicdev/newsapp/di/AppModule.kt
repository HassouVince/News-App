package fr.systemathicdev.newsapp.di

import fr.systemathicdev.data.utils.IntentFactory
import org.koin.dsl.module.module

val appModule = module(override = true) {
    factory<IntentFactory>{ IntentFactory() }
}