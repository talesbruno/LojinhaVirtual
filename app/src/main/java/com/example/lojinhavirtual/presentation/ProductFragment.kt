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