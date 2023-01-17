package com.bondidos.rsschoolcurrency.data

import com.bondidos.rsschoolcurrency.data.network_service.CurrencyService
import com.bondidos.rsschoolcurrency.domain.models.Currency
import com.bondidos.rsschoolcurrency.domain.repository.CurrencyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRepository { // Так будет красивее. Параметры конструкторов и методов начинаются с новой строки, если не влазят по длине
    override fun fetchAllCurrencies(): Flow<List<Currency>> {
        return flow {
            while (true) {
                emit(currencyService.fetchAll())
                delay(5000)
            }
        }
    }
}
