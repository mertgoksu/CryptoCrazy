package com.mertg.koincryptocrazy.repository

import com.mertg.koincryptocrazy.model.CryptoModel
import com.mertg.koincryptocrazy.service.CryptoAPI
import com.mertg.koincryptocrazy.util.Resource

class CryptoDownloadImpl(private val api: CryptoAPI) : CryptoDownload {

    override suspend fun downloadCryptos(): Resource<List<CryptoModel>> {

        return try{
            val response = api.getData()

            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else{
                Resource.error("Error",null)
            }
        } catch (e: java.lang.Exception){
            Resource.error("No Data!",null)
        }

    }
}