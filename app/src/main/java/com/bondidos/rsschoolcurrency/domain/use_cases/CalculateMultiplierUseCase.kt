package com.bondidos.rsschoolcurrency.domain.use_cases

import com.bondidos.rsschoolcurrency.domain.models.CalculateMultiplierWrapper

class CalculateMultiplierUseCase : InOutUseCase<CalculateMultiplierWrapper, Double> {
    override fun invoke(params: CalculateMultiplierWrapper): Double {
        val itemInFocus = params.latestList.find {
            it.baseName == params.basename
        }
        itemInFocus ?: throw java.lang.IllegalArgumentException()
        val newCurrencyValue = params.text.toString().toDouble()
        val previousCurrencyValue = itemInFocus.rate.toDouble()
        return newCurrencyValue/previousCurrencyValue
    }
}