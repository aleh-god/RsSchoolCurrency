package com.bondidos.rsschoolcurrency.domain.models

// Все же для удобства чтения кода, оборачивать нужно три и больше параметра
data class SortListWrapper(
    val item: CurrencyUi,
    val list: List<CurrencyUi>,
)
