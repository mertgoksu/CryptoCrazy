package com.mertg.koincryptocrazy.di

import com.mertg.koincryptocrazy.repository.CryptoDownload
import com.mertg.koincryptocrazy.repository.CryptoDownloadImpl
import com.mertg.koincryptocrazy.service.CryptoAPI
import com.mertg.koincryptocrazy.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    //singleton scope
    single {

        val BASE_URL = "https://api.coingecko.com/api/v3/"

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

    }

    single<CryptoDownload> {
        CryptoDownloadImpl(get())
    }

    viewModel{
        CryptoViewModel(get())
    }

    //factory -> everytime we inject -> new instance
}