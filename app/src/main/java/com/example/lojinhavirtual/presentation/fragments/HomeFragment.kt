package com.example.lojinhavirtual.presentation.fragments

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
import com.example.lojinhavirtual.databinding.HomeFragmentBinding
import com.example.lojinhavirtual.presentation.ProductViewModel

class HomeFragment: Fragment() {
    private val viewModel: ProductViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observar categorias
        viewModel.categoriasLiveData.observe(viewLifecycleOwner){
            val categoryAdapter = CategoryAdapter(it)
            val categoriaRecyclerView: RecyclerView = view.findViewById(R.id.rv_menu)
            categoriaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            categoriaRecyclerView.adapter = categoryAdapter
        }

        // Observar produtos por categoria
        viewModel.produtosPorCategoriaLiveData.observe(viewLifecycleOwner) {
            val produtosPorCategoriaAdapter = ProductAdapter(it)
            val produtosPorCategoriaRecyclerView: RecyclerView = view.findViewById(R.id.rv_main)
            produtosPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            produtosPorCategoriaRecyclerView.adapter = produtosPorCategoriaAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}