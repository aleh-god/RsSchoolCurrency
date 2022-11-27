package com.bondidos.rsschoolcurrency.domain.mapers

import com.bondidos.rsschoolcurrency.R
import com.bondidos.rsschoolcurrency.domain.models.Currency
import com.bondidos.rsschoolcurrency.domain.models.CurrencyUi

class DomainToUiCurrencyMapper : BaseMapper<List<Currency>, List<CurrencyUi>> {

    override fun invoke(params: List<Currency>): List<CurrencyUi> =
        params.map { currency ->
            CurrencyUi(
                imageResource = obtainImageByName(currency.baseName),
                currencyDescription = obtainDescriptionByName(currency.baseName),
                rate = String.format("%.5f", currency.rate),
                baseName = currency.baseName,
            )
        }

    companion object {
        private val images: Map<String, Int> = mapOf(
            "USD" to R.drawable.ic_usd_flag,
            "EUR" to R.drawable.ic_eur_flag,
            "CHF" to R.drawable.ic_chf_flag,
            "HRK" to R.drawable.ic_hrk_flag,
            "MXN" to R.drawable.ic_mxn_flag,
            "ZAR" to R.drawable.ic_zar_flag,
            "CNY" to R.drawable.ic_cny_flag,
            "THB" to R.drawable.ic_thb_flag,
            "AUD" to R.drawable.ic_aud_flag,
            "ILS" to R.drawable.ic_ils_flag,
            "KRW" to R.drawable.ic_krw_flag,
            "JPY" to R.drawable.ic_jpy_flag,
            "PLN" to R.drawable.ic_pln_flag,
            "GBP" to R.drawable.ic_gbp_flag,
            "IDR" to R.drawable.ic_idr_flag,
            "HUF" to R.drawable.ic_huf_flag,
            "PHP" to R.drawable.ic_php_flag,
            "TRY" to R.drawable.ic_try_flag,
            "RUB" to R.drawable.ic_rub_flag,
            "HKD" to R.drawable.ic_hkd_flag,
            "ISK" to R.drawable.ic_isk_flag,
            "DKK" to R.drawable.ic_dkk_flag,
            "CAD" to R.drawable.ic_cad_flag,
            "MYR" to R.drawable.ic_myr_flag,
            "BGN" to R.drawable.ic_bgn_flag,
            "NOK" to R.drawable.ic_nok_flag,
            "RON" to R.drawable.ic_ron_flag,
            "SGD" to R.drawable.ic_sgd_flag,
            "CZK" to R.drawable.ic_czk_flag,
            "SEK" to R.drawable.ic_sek_flag,
            "NZD" to R.drawable.ic_nzd_flag,
            "BRL" to R.drawable.ic_brl_flag,
            "INR" to R.drawable.ic_inr_flag,
        )

        private val strings: Map<String, Int> = mapOf(
            "USD" to R.string.currency_usd_name,
            "EUR" to R.string.currency_eur_name,
            "CHF" to R.string.currency_chf_name,
            "HRK" to R.string.currency_hrk_name,
            "MXN" to R.string.currency_mxn_name,
            "ZAR" to R.string.currency_zar_name,
            "CNY" to R.string.currency_cny_name,
            "THB" to R.string.currency_thb_name,
            "AUD" to R.string.currency_aud_name,
            "ILS" to R.string.currency_ils_name,
            "KRW" to R.string.currency_krw_name,
            "JPY" to R.string.currency_jpy_name,
            "PLN" to R.string.currency_pln_name,
            "GBP" to R.string.currency_gbp_name,
            "IDR" to R.string.currency_idr_name,
            "HUF" to R.string.currency_huf_name,
            "PHP" to R.string.currency_php_name,
            "TRY" to R.string.currency_try_name,
            "RUB" to R.string.currency_rub_name,
            "HKD" to R.string.currency_hkd_name,
            "ISK" to R.string.currency_isk_name,
            "DKK" to R.string.currency_dkk_name,
            "CAD" to R.string.currency_cad_name,
            "MYR" to R.string.currency_myr_name,
            "BGN" to R.string.currency_bgn_name,
            "NOK" to R.string.currency_nok_name,
            "RON" to R.string.currency_ron_name,
            "SGD" to R.string.currency_sgd_name,
            "CZK" to R.string.currency_czk_name,
            "SEK" to R.string.currency_sek_name,
            "NZD" to R.string.currency_nzd_name,
            "BRL" to R.string.currency_brl_name,
            "INR" to R.string.currency_inr_name,
            "BYN" to R.string.currency_byn_name,
        )

        private fun obtainImageByName(base: String): Int =
            images[base] ?: R.drawable.ic_launcher_foreground

        private fun obtainDescriptionByName(base: String): Int = strings[base] ?: R.string.unknown
    }
}
