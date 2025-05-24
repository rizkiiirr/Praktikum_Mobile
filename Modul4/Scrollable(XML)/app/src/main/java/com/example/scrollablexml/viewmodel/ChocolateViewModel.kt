package com.example.scrollablexml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scrollablexml.data.Chocolate
import com.example.scrollablexml.data.ChocolateProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class ChocolateViewModel(private val source: String) : ViewModel() {

    private val _chocolateList = MutableStateFlow<List<Chocolate>>(emptyList())
    val chocolateList: StateFlow<List<Chocolate>> get() = _chocolateList

    private val _selectedChocolate = MutableStateFlow<Chocolate?>(null)

    init {
        Timber.d("ViewModel created with source: $source")
        _chocolateList.value = ChocolateProvider.chocolateList
        Timber.d("Chocolate list loaded: ${_chocolateList.value}")
    }

    fun onChocolateSelected(chocolate: Chocolate) {
        _selectedChocolate.value = chocolate
        Timber.d("Selected chocolate: $chocolate")
    }
}
