package com.bondidos.rsschoolcurrency.domain.models

data class CalculateMultiplierWrapper(
    val text: CharSequence,
    val basename: String,
    val latestList: List<CurrencyUi>,
)