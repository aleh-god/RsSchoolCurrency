package com.bondidos.rsschoolcurrency.domain.mapers

import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi
import com.bondidos.rsschoolcurrency.domain.models.SortListWrapper

class MoveToTheTopMapper : BaseMapper<SortListWrapper, List<CurrencyUi>> {
    override fun invoke(params: SortListWrapper): List<CurrencyUi> {
        val clickedItem: CurrencyUi? = params.list.find {
            it.baseName == params.item.baseName
        }
        clickedItem?.let {
            return params.list.toMutableList().apply {
                remove(clickedItem)
                add(index = 0, clickedItem.copy(rate = ""))
            }
        }
        return params.list
    }
}