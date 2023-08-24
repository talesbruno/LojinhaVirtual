package com.example.lojinhavirtual.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.GetCategoryUseCase
import com.example.lojinhavirtual.domain.GetProductForCategoryUseCase
import com.example.lojinhavirtual.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getProductForCategoryUseCase: GetProductForCategoryUseCase
    ) : ViewModel() {

    private val _categoriasLiveData = MutableLiveData<List<Category>>()
    val categoriasLiveData: LiveData<List<Category>> = _categoriasLiveData

    private val _produtosPorCategoriaLiveData = MutableLiveData<List<Product>>()
    val produtosPorCategoriaLiveData: LiveData<List<Product>> = _produtosPorCategoriaLiveData

    private val _fakeCart = MutableLiveData<Double>()
    val fakeCart: LiveData<Double> = _fakeCart

    init {
        carregarCategorias()
    }

    private fun carregarCategorias() {
        viewModelScope.launch {
            val categorias = getCategoryUseCase.execute()
            _categoriasLiveData.postValue(categorias)
        }
    }

    fun carregarProdutosPorCategoria(categoria: String) {
        viewModelScope.launch {
            val produtosPorCategoria = getProductForCategoryUseCase.execute(categoria)
            _produtosPorCategoriaLiveData.postValue(produtosPorCategoria)
        }
    }

    fun addToCart(amount: Double) {
        val currentTotal = _fakeCart.value ?: 0.0 // Valor atual do LiveData ou 0 se for nulo
        _fakeCart.value = currentTotal + amount // Adiciona o novo valor ao total
    }
}
