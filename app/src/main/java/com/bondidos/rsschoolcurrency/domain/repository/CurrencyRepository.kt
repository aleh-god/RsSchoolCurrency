// Возможно, пакет нужно назвать не репозиторий. Так как с помощью интерфейсов можно обращаться не только к нему в ДАТА слое
package com.bondidos.rsschoolcurrency.domain.repository

import com.bondidos.rsschoolcurrency.domain.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun fetchAllCurrencies(): Flow<List<Currency>>
}
