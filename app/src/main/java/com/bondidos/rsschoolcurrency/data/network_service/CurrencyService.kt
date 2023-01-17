package com.bondidos.rsschoolcurrency.data.network_service

import com.bondidos.rsschoolcurrency.data.model.CurrencyApi
import retrofit2.http.GET

interface CurrencyService {

        // TODO("часть строки можно убрать в BASE URL")
    @GET("/myWebsiteBackend/api/currency/")
    suspend fun fetchAll(): List<CurrencyApi>
}
