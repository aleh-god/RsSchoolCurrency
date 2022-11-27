package com.bondidos.rsschoolcurrency.data.model

import com.bondidos.rsschoolcurrency.domain.models.Currency
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CurrencyApi(
    @Json(name = "date")
    override val date: Long,
    @Json(name = "rate")
    override val rate: Double,
    @Json(name = "base")
    override val baseName: String,
) : Currency