package com.bondidos.rsschoolcurrency.presentation.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.rsschoolcurrency.databinding.CurrencyItemBinding
import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class CurrencyHolder(
    binding: CurrencyItemBinding,
    private val onTap: (CurrencyUi) -> Unit,
    private val onTextChange: (CharSequence?, String) -> Unit,
    private val focused: (View?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    var item: CurrencyUi? = null

    // А откуда такое изобретение? Вроде прикольное, но персиком чую, что это кастыль. Есть binding, все кроме него - ненужное усложнение.
    // Вроде как получешь биндинг извне и отдаешь назад ссылки на вьюхи, здесь в коде не используешь. Зачем этот крюк? Холдить ссылки? Так их биндинг уже холдит.
    val image: ImageView = binding.currencyImage
    val abrName: MaterialTextView = binding.currencyBaseName
    val name: MaterialTextView = binding.currencyName
    val rate: TextInputEditText = binding.rateTextField

    init {
        binding.root.setOnClickListener {
            focused.invoke(null)
        }
        rate.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                focused.invoke(view)
                item?.let {
                    onTap.invoke(it)
                }
            }
        }
        rate.doOnTextChanged { text, _, _, _ ->
            if (rate.hasFocus() && !text.isNullOrBlank()) onTextChange.invoke(
                text,
                item?.baseName ?: ""
            )
        }
    }
}
