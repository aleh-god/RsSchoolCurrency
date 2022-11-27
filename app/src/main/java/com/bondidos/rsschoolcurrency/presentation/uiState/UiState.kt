package com.bondidos.rsschoolcurrency.presentation.uiState

import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi

sealed class UiState {
    object Init : UiState()
    object Loading : UiState()
    data class Loaded(val data: List<CurrencyUi>) : UiState()
}