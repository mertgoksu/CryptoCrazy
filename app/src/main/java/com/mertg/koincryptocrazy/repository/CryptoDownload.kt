package com.mertg.koincryptocrazy.repository

import com.mertg.koincryptocrazy.model.CryptoModel
import com.mertg.koincryptocrazy.util.Resource

interface CryptoDownload {

    suspend fun downloadCryptos() : Resource<List<CryptoModel>>
}