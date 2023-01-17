package com.bondidos.rsschoolcurrency.domain.use_cases

import com.bondidos.rsschoolcurrency.domain.models.CalculateMultiplierWrapper

class CalculateMultiplierUseCase : InOutUseCase<CalculateMultiplierWrapper, Double> {
    override fun invoke(params: CalculateMultiplierWrapper): Double {
        val itemInFocus = params.latestList.find {
            it.baseName == params.basename
        }
        // Сайд эффект в домейн слое или плохой нейминг метода
        itemInFocus ?: throw java.lang.IllegalArgumentException()
        // Эти методы могут выбросить исключение. Возможно, где-то они отлавливаются, но по архитектуре это неправильно.
        val newCurrencyValue = params.text.toString().toDouble()
        val previousCurrencyValue = itemInFocus.rate.toDouble()
        return newCurrencyValue/previousCurrencyValue
    }
}
