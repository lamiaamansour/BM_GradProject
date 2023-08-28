package com.example.currencyconverter.data.remote.api

import com.example.currencyconverter.domain.ConversionRate
import com.example.currencyconverter.domain.ConversionResult
import com.example.currencyconverter.domain.Currency
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("currencies")
    suspend fun getCurrencies(): List<Currency>

    @GET("currencies/{base}/{target}")
    suspend fun getConversionRate(@Path("base") base: String, @Path("target") target: String): ConversionRate

    @GET("currencies/{base}/{target}/{amount}")
    suspend fun getConversionResult(@Path("base") base: String, @Path("target") target: String, @Path("amount") amount: Double): ConversionResult
}