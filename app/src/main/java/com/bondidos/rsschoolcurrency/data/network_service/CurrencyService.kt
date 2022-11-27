package com.bondidos.rsschoolcurrency.data.network_service

import com.bondidos.rsschoolcurrency.data.model.CurrencyApi
import retrofit2.http.GET

interface CurrencyService {

    @GET("/myWebsiteBackend/api/currency/")
    suspend fun fetchAll(): List<CurrencyApi>
}