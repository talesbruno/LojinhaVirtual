package com.example.lojinhavirtual.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.R

class ProductFragment : Fragment() {

    private val viewModel: ProdutoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val adapter = ProdutoAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Carregar categorias
        viewModel.carregarCategorias()

        // Observar categorias
        viewModel.categoriasLiveData.observe(viewLifecycleOwner) { categorias ->
            // Aqui você pode atualizar a UI com a lista de categorias
            // Por exemplo, preenchendo o menu de categorias
        }
    }
}

class ProdutoFragment : Fragment() {

    private val viewModel: ProdutoViewModel by viewModels()
    private val categoriaAdapter = CategoriaAdapter { categoriaSelecionada ->
        viewModel.carregarProdutosPorCategoria(categoriaSelecionada)
    }
    private val produtosPorCategoriaAdapter = ProdutosAdapter(emptyList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriaRecyclerView: RecyclerView = view.findViewById(R.id.categoriaRecyclerView)
        val produtosPorCategoriaRecyclerView: RecyclerView = view.findViewById(R.id.produtosPorCategoriaRecyclerView)

        categoriaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        produtosPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        categoriaRecyclerView.adapter = categoriaAdapter
        produtosPorCategoriaRecyclerView.adapter = produtosPorCategoriaAdapter

        // Carregar categorias
        viewModel.carregarCategorias()

        // Observar categorias
        viewModel.categoriasLiveData.observe(viewLifecycleOwner) { categorias ->
            categoriaAdapter.setCategorias(categorias)
        }

        // Observar produtos por categoria
        viewModel.produtosPorCategoriaLiveData.observe(viewLifecycleOwner) { produtos ->
            produtosPorCategoriaAdapter.atualizarProdutos(produtos)
        }
    }
}
