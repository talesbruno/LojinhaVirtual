package com.example.lojinhavirtual.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.presentation.adapters.CategoryAdapter
import com.example.lojinhavirtual.presentation.adapters.MenuAdapter
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.databinding.HomeFragmentBinding
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.OnProductClickListener
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.presentation.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: ProductViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var menuAdapter: MenuAdapter

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

        val categoryRecyclerView: RecyclerView = binding.rvMain
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        categoryRecyclerView.adapter = categoryAdapter

        val menuRecyclerView: RecyclerView = binding.rvMenu
        menuRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        menuRecyclerView.adapter = menuAdapter

        viewModel.categoriasLiveData.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.updateCategories(categories)
            menuAdapter.updateMenu(categories)
        }

        categoryAdapter.onProductClickListener = object : OnProductClickListener {
            override fun onProductClick(product: Product) {
                val directions = HomeFragmentDirections.goToDetailsFragment(product)
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
