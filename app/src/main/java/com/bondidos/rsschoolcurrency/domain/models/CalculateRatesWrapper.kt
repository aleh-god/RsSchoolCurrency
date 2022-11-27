package com.bondidos.rsschoolcurrency.domain.models

data class CalculateRatesWrapper(
    val rate: Double,
    val list: List<CurrencyUi>,
)
