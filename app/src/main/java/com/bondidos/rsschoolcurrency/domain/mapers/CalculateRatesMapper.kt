package com.bondidos.rsschoolcurrency.domain.mapers

import com.bondidos.rsschoolcurrency.domain.models.CalculateRatesWrapper
import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi

class CalculateRatesMapper : BaseMapper<CalculateRatesWrapper, List<CurrencyUi>> {
    override fun invoke(params: CalculateRatesWrapper): List<CurrencyUi> {
        return params.list.map { currencyUi ->
            if (params.list.indexOf(currencyUi) == zero) {
                return@map  currencyUi
            }
            val currentRate = currencyUi.rate.toDouble()
            val newRate = (currentRate * params.rate).toString()
            currencyUi.copy(rate = newRate)
        }
    }
    companion object{
        private const val zero = 0
    }
}