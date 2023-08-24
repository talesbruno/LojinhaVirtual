package com.example.lojinhavirtual.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lojinhavirtual.databinding.HomeFragmentBinding
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.OnProductClickListener
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.presentation.ProductViewModel
import com.example.lojinhavirtual.presentation.adapters.CategoryAdapter
import com.example.lojinhavirtual.presentation.adapters.MenuAdapter
import dagger.hilt.android.AndroidEntryPoint

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
        setupSearchBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViews() {
        categoryAdapter = CategoryAdapter(emptyList(), this)
        menuAdapter = MenuAdapter(emptyList(),this)

        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }

        binding.rvMenu.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = menuAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.categoriasLiveData.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.updateCategories(categories)
            menuAdapter.updateMenu(categories)
        }

        viewModel.searchResultsLiveData.observe(viewLifecycleOwner) { searchResults ->
            categoryAdapter.applyFilter("") // Limpar o filtro das categorias
            categoryAdapter.updateCategories(createDummyCategories(searchResults))
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

    private fun createDummyCategories(products: List<Product>): List<Category> {
        // Aqui, você cria uma categoria fictícia que contém apenas os produtos da pesquisa
        return listOf(Category("Resultado da Pesquisa", "", products))
    }

    private fun setupSearchBar() {
        binding.searchTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchProductsByName(s.toString())
                categoryAdapter.applyFilter(s.toString())
            }
        })
    }

    override fun onProductClick(product: Product) {
        val directions = HomeFragmentDirections.goToDetailsFragment(product)
        findNavController().navigate(directions)
    }

    override fun onCategoryClick(category: Category) {
        viewModel.filterProductsByCategory(category)
    }
}

