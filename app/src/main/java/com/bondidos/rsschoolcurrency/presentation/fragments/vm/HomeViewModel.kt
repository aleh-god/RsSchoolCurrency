package com.bondidos.rsschoolcurrency.presentation.fragments.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.rsschoolcurrency.domain.use_cases.CalculateMultiplierUseCase
import com.bondidos.rsschoolcurrency.domain.use_cases.FetchAllCurrenciesUseCase
import com.bondidos.rsschoolcurrency.domain.mapers.CalculateRatesMapper
import com.bondidos.rsschoolcurrency.domain.mapers.DomainToUiCurrencyMapper
import com.bondidos.rsschoolcurrency.domain.mapers.MoveToTheTopMapper
import com.bondidos.rsschoolcurrency.domain.models.CalculateMultiplierWrapper
import com.bondidos.rsschoolcurrency.domain.models.CalculateRatesWrapper
import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi
import com.bondidos.rsschoolcurrency.domain.models.SortListWrapper
import com.bondidos.rsschoolcurrency.presentation.uiState.UiState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val fetchAllCurrencies: FetchAllCurrenciesUseCase,
    private val calculateMultiplier: CalculateMultiplierUseCase,
    private val uiModelMapper: DomainToUiCurrencyMapper,
    private val moveToTheTopMapper: MoveToTheTopMapper,
    private val calculateRatesMapper: CalculateRatesMapper
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Init)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<String> = MutableSharedFlow()
    val uiEvent: SharedFlow<String> = _uiEvent.asSharedFlow()

    private var currencyJob: Job? = null
    private var latestList: List<CurrencyUi>? = null

    init {
        currencyJob = currencyJob()
    }

    private fun currencyJob(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            fetchAllCurrencies()
                .onStart { _uiState.emit(UiState.Loading) }
                .catch { _uiEvent.emit("Check internet connection") }
                .collect {
                    latestList = uiModelMapper(it)
                    _uiState.emit(UiState.Loaded(data = latestList ?: emptyList()))
                }
        }
    }

    fun onItemTapCallback(item: CurrencyUi) {
        currencyJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            latestList?.let { latestList ->
                val wrapper = SortListWrapper(item, latestList)
                val sortedlist: List<CurrencyUi> = moveToTheTopMapper(wrapper)
                _uiState.emit(UiState.Loaded(data = sortedlist))
            }
        }

    }

    fun resumeJob() {
        if (currencyJob?.isCancelled == true) {
            currencyJob = currencyJob()
        }
    }

    fun onTextChanged(text: CharSequence?, baseName: String) {
        if (text.isNullOrBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            latestList?.let { it ->
                val wrapper = CalculateMultiplierWrapper(text, baseName, it)
                val multiplier = calculateMultiplier(wrapper)
                val currentList = (_uiState.value as UiState.Loaded).data

                val calculateRatesWrapper = CalculateRatesWrapper(
                    multiplier,
                    currentList
                )
                val multipliedList = calculateRatesMapper(calculateRatesWrapper)
                _uiState.emit(UiState.Loaded(data = multipliedList))

            }
        }
    }
}
