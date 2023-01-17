package com.bondidos.rsschoolcurrency.domain.use_cases

import com.bondidos.rsschoolcurrency.domain.models.Currency
import com.bondidos.rsschoolcurrency.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) : OutUseCase<Flow<List<Currency>>> {
    // Пустой юз-кейс с пробросом метода из репозитория. Такое было модно в 2020 году. В 21 критиковали сеньоры. В 22 напишу уже и я. Должен быть отдельный прямой интерфейс к репе.
    override fun invoke(): Flow<List<Currency>> = repository.fetchAllCurrencies()
}
