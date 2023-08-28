package com.example.currencyconverter.domain


data class Currency(val name: String, val code: String, val icon_url: String)

data class ConversionRate(val base_code: String, val target_code: String, val conversion_rate: Double)

data class ConversionResult(val base_code: String, val target_code: String, val conversion_rate: Double, val conversion_result: Double)