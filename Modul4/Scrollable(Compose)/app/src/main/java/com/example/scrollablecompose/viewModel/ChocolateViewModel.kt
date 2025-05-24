package com.example.scrollablecompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scrollablecompose.data.Chocolate
import com.example.scrollablecompose.data.ChocolateProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class ChocolateViewModelFactory(private val param: String) : ViewModelProvider.Factory {
    override fun <Choco : ViewModel> create(modelClass: Class<Choco>): Choco {
        if (modelClass.isAssignableFrom(ChocolateViewModel::class.java)) {
            return ChocolateViewModel(param) as Choco
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ChocolateViewModel(private val param: String) : ViewModel() {

    private val _chocolateList = MutableStateFlow<List<Chocolate>>(emptyList())
    val chocolateList: StateFlow<List<Chocolate>> = _chocolateList

    init {
        loadChocolates()
    }

    private fun loadChocolates() {
        _chocolateList.value = ChocolateProvider.chocolateList
        Timber.d("Chocolate list loaded: ${_chocolateList.value.size} items")

    }

    fun getChocolateById(id: Int): Chocolate? {
        return _chocolateList.value.find { it.no == id }
    }
}