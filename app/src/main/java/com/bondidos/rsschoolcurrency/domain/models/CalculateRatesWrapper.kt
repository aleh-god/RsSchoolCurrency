package com.bondidos.rsschoolcurrency.domain.models

data class CalculateRatesWrapper(
    val rate: Double, // Для точных вычислений лучше использовать BigDecimal
    val list: List<CurrencyUi>,
)
