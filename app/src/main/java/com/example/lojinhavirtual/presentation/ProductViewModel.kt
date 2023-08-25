package com.example.lojinhavirtual.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.GetCategoryUseCase
import com.example.lojinhavirtual.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    ) : ViewModel() {

    private val _categoriasLiveData = MutableLiveData<List<Category>>()
    val categoriasLiveData: LiveData<List<Category>> = _categoriasLiveData

    private val _fakeCart = MutableLiveData<Double>()
    val fakeCart: LiveData<Double> = _fakeCart

    private val _searchResultsLiveData = MutableLiveData<List<Product>>()
    val searchResultsLiveData: LiveData<List<Product>> = _searchResultsLiveData

    init {
        carregarCategorias()
    }

    private fun carregarCategorias() {
        viewModelScope.launch {
            val categorias = getCategoryUseCase.execute()
            _categoriasLiveData.postValue(categorias)
        }
    }

    fun searchProductsByName(query: String) {
        viewModelScope.launch {
            val searchResults = getCategoryUseCase.searchProductsByName(query)
            _searchResultsLiveData.postValue(searchResults)
        }
    }
    fun filterProductsByCategory(category: Category) {
        viewModelScope.launch {
            val filteredProducts = getCategoryUseCase.filterProductsByCategory(category)
            _searchResultsLiveData.postValue(filteredProducts)
        }
    }
    fun loadAllCategories() {
        carregarCategorias()
    }

    fun addToCart(amount: Double) {
        val currentTotal = _fakeCart.value ?: 0.0
        val newTotal = currentTotal + amount
        _fakeCart.value = newTotal
    }
}
