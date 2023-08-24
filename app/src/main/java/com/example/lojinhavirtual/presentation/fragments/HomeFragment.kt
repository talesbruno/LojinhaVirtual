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
class HomeFragment : Fragment(), OnProductClickListener {

    private val viewModel: ProductViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var menuAdapter: MenuAdapter

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
        setupRecyclerViews()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViews() {
        categoryAdapter = CategoryAdapter(emptyList(), this)
        menuAdapter = MenuAdapter(emptyList())

        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }

        binding.rvMenu.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = menuAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.categoriasLiveData.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.updateCategories(categories)
            menuAdapter.updateMenu(categories)
        }

        viewModel.fakeCart.observe(viewLifecycleOwner) { cartTotal ->
            if (cartTotal > 0) {
                binding.cartComponent.visibility = View.VISIBLE
                binding.cartTotal.text = "R$ $cartTotal"
            } else {
                binding.cartComponent.visibility = View.GONE
            }
        }
    }

    override fun onProductClick(product: Product) {
        val directions = HomeFragmentDirections.goToDetailsFragment(product)
        findNavController().navigate(directions)
    }
}

