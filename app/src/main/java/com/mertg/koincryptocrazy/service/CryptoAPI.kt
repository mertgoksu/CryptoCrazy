package com.mertg.koincryptocrazy.service

import com.mertg.koincryptocrazy.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    //coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1
    //BASE URL = https://api.coingecko.com/api/v3/

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1")
    suspend fun getData() : Response<List<CryptoModel>>

}