package com.bondidos.rsschoolcurrency.domain.mapers

import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi
import com.bondidos.rsschoolcurrency.domain.models.SortListWrapper

class MoveToTheTopMapper : BaseMapper<SortListWrapper, List<CurrencyUi>> {
    override fun invoke(params: SortListWrapper): List<CurrencyUi> {
        val clickedItem: CurrencyUi? = params.list.find {
            it.baseName == params.item.baseName
        }
        // Наверное, цепочку можно было продолжить без val
        clickedItem?.let {
            // Возвращаешь мутабельный лист, пусть и под интерфейсом листа. 
            return params.list.toMutableList().apply {
                // Можно было сделать через .map
                remove(clickedItem)
                add(index = 0, clickedItem.copy(rate = ""))
            }
        }
        return params.list
    }
}
