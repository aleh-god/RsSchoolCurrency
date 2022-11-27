package com.bondidos.rsschoolcurrency.domain.use_cases

import com.bondidos.rsschoolcurrency.domain.models.Currency
import com.bondidos.rsschoolcurrency.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) : OutUseCase<Flow<List<Currency>>> {
    override fun invoke(): Flow<List<Currency>> = repository.fetchAllCurrencies()
}