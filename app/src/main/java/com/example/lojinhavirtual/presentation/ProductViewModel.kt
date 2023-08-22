package com.example.lojinhavirtual.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.GetCategoryUseCase
import com.example.lojinhavirtual.domain.Product

class ProductViewModel (private val getCategoryUseCase: GetCategoryUseCase) : ViewModel() {

    private val _categoriasLiveData = MutableLiveData<List<Category>>()
    val categoriasLiveData: LiveData<List<Category>> = _categoriasLiveData

    fun carregarCategorias() {
        viewModelScope.launch {
            val categorias = getCategoryUseCase.execute()
            _categoriasLiveData.value = categorias
        }
    }
}

class ProductViewModel(private val getCategoryUseCase: GetCategoryUseCase) : ViewModel() {

    private val _categoriasLiveData = MutableLiveData<List<Category>>()
    val categoriasLiveData: LiveData<List<Category>> = _categoriasLiveData

    private val _produtosPorCategoriaLiveData = MutableLiveData<List<Product>>()
    val produtosPorCategoriaLiveData: LiveData<List<Product>> = _produtosPorCategoriaLiveData

    fun carregarCategorias() {
        viewModelScope.launch {
            val categorias = getCategoryUseCase.execute()
            _categoriasLiveData.postValue(categorias)
        }
    }

    fun carregarProdutosPorCategoria(categoria: String) {
        viewModelScope.launch {
            val produtosPorCategoria = produtoRepository.getProdutosPorCategoria(categoria)
            _produtosPorCategoriaLiveData.postValue(produtosPorCategoria)
        }
    }
}
