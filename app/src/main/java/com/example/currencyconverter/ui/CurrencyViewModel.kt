package com.example.currencyconverter.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.local.dp.CachedCurrency
import com.example.currencyconverter.data.remote.api.ApiService
import com.example.currencyconverter.data.remote.api.RetrofitClient
import com.example.currencyconverter.domain.CurrencyRepository
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {

    private val _remoteCurrencies = MutableLiveData<List<CachedCurrency>>()
    val remoteCurrencies: LiveData<List<CachedCurrency>> = _remoteCurrencies

    lateinit var  cachedCurrencies : List<CachedCurrency>
    init {
        viewModelScope.launch {
            fetchRemoteCurrencies()
        }
    }

    suspend fun fetchRemoteCurrencies() {
        try {
            val apiService = RetrofitClient.createService(ApiService::class.java)
            val currencies = apiService.getCurrencies()
            val cachedCurrencies = currencies.map { currency ->
                CachedCurrency(currency.code, currency.flagUrl, currency.desc)
            }
            _remoteCurrencies.postValue(cachedCurrencies)
            currencyRepository.insertCurrencies(cachedCurrencies)

            Log.d("CurrencyViewModel", "Remote Data: $cachedCurrencies")
        } catch (e: Exception) {
            Log.e("CurrencyViewModel", "Error fetching remote data: ${e.message}")


            fetchCachedCurrencies()
        }
    }

    private fun fetchCachedCurrencies() {
        cachedCurrencies = currencyRepository.getCachedCurrenciesLiveData().value!!
        _remoteCurrencies.postValue(cachedCurrencies)


        Log.d("CurrencyViewModel", "Cached Data: $cachedCurrencies")
    }
}