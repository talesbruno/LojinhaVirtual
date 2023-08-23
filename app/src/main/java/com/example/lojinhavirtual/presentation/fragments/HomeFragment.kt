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
import com.example.lojinhavirtual.MenuAdapter
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

        val categoryAdapter = CategoryAdapter(emptyList())
        val categoryRecyclerView: RecyclerView = view.findViewById(R.id.rv_main)
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        categoryRecyclerView.adapter = categoryAdapter

        val menuAdapter = MenuAdapter(emptyList())
        val menuRecyclerView: RecyclerView = view.findViewById(R.id.rv_menu)
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        menuRecyclerView.adapter = menuAdapter

        viewModel.categoriasLiveData.observe(viewLifecycleOwner){
            categoryAdapter.updateCategories(it)
            menuAdapter.updateMenu(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}