package com.bondidos.rsschoolcurrency.presentation.fragments.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bondidos.rsschoolcurrency.domain.use_cases.CalculateMultiplierUseCase
import com.bondidos.rsschoolcurrency.domain.use_cases.FetchAllCurrenciesUseCase
import com.bondidos.rsschoolcurrency.domain.mapers.CalculateRatesMapper
import com.bondidos.rsschoolcurrency.domain.mapers.DomainToUiCurrencyMapper
import com.bondidos.rsschoolcurrency.domain.mapers.MoveToTheTopMapper
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    private val fetchAllCurrencies: FetchAllCurrenciesUseCase,
    private val calculateMultiplier: CalculateMultiplierUseCase,
    private val uiModelMapper: DomainToUiCurrencyMapper,
    private val moveToTeTopMapper: MoveToTheTopMapper,
    private val calculateRatesMapper: CalculateRatesMapper,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            fetchAllCurrencies,
            calculateMultiplier,
            uiModelMapper,
            moveToTeTopMapper,
            calculateRatesMapper,
        ) as T
    }
}