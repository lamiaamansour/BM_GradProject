package com.example.currencyconverter.persentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.CurrencyViewModel


data class CurrencyItem(val currencyCode: String, @DrawableRes val flagResId: Int)

@Composable
fun CurrencyDropdown(modifier: Modifier) {
    val currencies = listOf(
        CurrencyItem("USD", R.drawable.ic_united_states_of_america),
        CurrencyItem("EUR", R.drawable.ic_united_kingdom),
        CurrencyItem("JPY", R.drawable.ic_japan),
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedCurrencyIndex by remember { mutableStateOf(0) }

    val selectedCurrency = currencies.getOrNull(selectedCurrencyIndex)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Column(
            Modifier.clickable { expanded = !expanded }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (selectedCurrency != null) {
                    Image(
                        painter = painterResource(id = selectedCurrency.flagResId),
                        contentDescription = "Currency Flag",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                    )
                    Text(text = selectedCurrency.currencyCode)
                }
                
                Spacer(modifier = Modifier.width(60.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Localized description"
                )
            }

            if (expanded) {
                Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))

                currencies.forEachIndexed { index, currency ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCurrencyIndex = index
                            expanded = false
                        },
                        modifier = Modifier.padding(horizontal = 16.dp), text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = currency.flagResId),
                                    contentDescription = "Currency Flag",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 8.dp)
                                )
                                Text(text = currency.currencyCode)
                            }
                        })
                }
            }
        }
    }
}