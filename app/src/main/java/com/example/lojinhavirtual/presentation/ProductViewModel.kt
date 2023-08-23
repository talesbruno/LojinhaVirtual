package com.example.lojinhavirtual.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.GetCategoryUseCase
import com.example.lojinhavirtual.domain.GetProductForCategoryUseCase
import com.example.lojinhavirtual.domain.Product
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getProductForCategoryUseCase: GetProductForCategoryUseCase
    ) : ViewModel() {

    private val _categoriasLiveData = MutableLiveData<List<Category>>()
    val categoriasLiveData: LiveData<List<Category>> = _categoriasLiveData

    private val _produtosPorCategoriaLiveData = MutableLiveData<List<Product>>()
    val produtosPorCategoriaLiveData: LiveData<List<Product>> = _produtosPorCategoriaLiveData

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
}
