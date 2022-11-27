package com.bondidos.rsschoolcurrency.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bondidos.rsschoolcurrency.databinding.CurrencyItemBinding
import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi

class CurrencyAdapter(
    private val ontTap: (CurrencyUi) -> Unit,
    private val onTextChange: (CharSequence?, String) -> Unit,
    private val resumeJob: () -> Unit
) : ListAdapter<CurrencyUi, CurrencyHolder>(diffCallback) {

    private var focusedView: View? = null

    private fun handleFocus(view: View?) {
        focusedView?.clearFocus()
        focusedView = view
        if (view == null) resumeJob.invoke()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater, parent, false)
        return CurrencyHolder(binding, ontTap, onTextChange, ::handleFocus)
    }

    override fun onBindViewHolder(
        holder: CurrencyHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {

        val currencyItem = getItem(position)

        if (payloads.isNotEmpty()) {
            payloads.forEach { payload ->
                when (payload) {
                    RATE_CHANGED -> {
                        holder.item = currencyItem
                        holder.rate.setText(currencyItem.rate)
                    }
                }
            }
        } else {
            holder.item = currencyItem
            holder.abrName.text = currencyItem.baseName
            holder.image.setImageResource(currencyItem.imageResource)
            holder.name.setText(currencyItem.currencyDescription)
            holder.rate.setText(currencyItem.rate)
        }
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    companion object {
        const val RATE_CHANGED = 1
        val diffCallback = object : DiffUtil.ItemCallback<CurrencyUi>() {
            override fun areItemsTheSame(
                oldItem: CurrencyUi,
                newItem: CurrencyUi
            ): Boolean =
                oldItem.baseName == newItem.baseName

            override fun areContentsTheSame(oldItem: CurrencyUi, newItem: CurrencyUi) =
                oldItem.rate == newItem.rate

            override fun getChangePayload(oldItem: CurrencyUi, newItem: CurrencyUi) =
                if (oldItem.baseName == newItem.baseName && oldItem.rate != newItem.rate) {
                    RATE_CHANGED
                } else null
        }
    }
}
