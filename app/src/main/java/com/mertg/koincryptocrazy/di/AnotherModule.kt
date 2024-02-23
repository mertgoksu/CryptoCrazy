package com.mertg.koincryptocrazy.di

import com.mertg.koincryptocrazy.view.ListFragment
import com.mertg.koincryptocrazy.view.MainActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val anotherModule = module{

    scope<ListFragment> {
        scoped(qualifier = named("hello")) {
            "Hello Kotlin"
        }

        scoped(qualifier = named("hello")) {
            "Hi Koin"
        }
    }

}