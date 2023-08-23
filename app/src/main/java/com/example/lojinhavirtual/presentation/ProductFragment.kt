package com.example.lojinhavirtual.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.CategoryAdapter
import com.example.lojinhavirtual.ProductAdapter
import com.example.lojinhavirtual.R

class ProductFragment : Fragment() {
    private val viewModel: ProductViewModel by viewModels()
    private val categoryAdapter = CategoryAdapter(emptyList())
    private val produtosPorCategoriaAdapter = ProductAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriaRecyclerView: RecyclerView = view.findViewById(R.id.rv_menu)
        val produtosPorCategoriaRecyclerView: RecyclerView = view.findViewById(R.id.rv_main)

        categoriaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        produtosPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        categoriaRecyclerView.adapter = categoryAdapter
        produtosPorCategoriaRecyclerView.adapter = produtosPorCategoriaAdapter

        // Carregar categorias
        viewModel.carregarCategorias()

        // Observar categorias
        viewModel.categoriasLiveData.observe(viewLifecycleOwner) { categorias ->
            categoryAdapter.
        }

        // Observar produtos por categoria
        viewModel.produtosPorCategoriaLiveData.observe(viewLifecycleOwner) { produtos ->
            produtosPorCategoriaAdapter.atualizarProdutos(produtos)
        }
    }
}
