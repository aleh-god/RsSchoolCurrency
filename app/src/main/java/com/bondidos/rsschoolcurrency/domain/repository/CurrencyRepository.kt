package com.bondidos.rsschoolcurrency.domain.repository

import com.bondidos.rsschoolcurrency.domain.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun fetchAllCurrencies(): Flow<List<Currency>>
}