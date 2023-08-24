package com.example.lojinhavirtual.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.lojinhavirtual.databinding.DetailsFragmentBinding
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.presentation.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: ProductViewModel by viewModels()
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        val product = args.model
        binding.apply {
            setupImageView(product.coverUrl)
            setupTitleAndDescription(product)
            setupQuantityButtons(product)
            setupAddToCartButton(product)
            setupCardDesc(product)
        }
    }

    private fun setupImageView(coverUrl: String) {
        val resources = requireContext().resources
        val coverResourceId =
            resources.getIdentifier(coverUrl, "drawable", requireContext().packageName)
        binding.detailProductImg.setImageResource(coverResourceId)
    }

    private fun setupTitleAndDescription(product: Product) {
        binding.detailTxtTitle.text = product.name
        binding.detailTxtDescription.text = product.name
    }

    private fun setupCardDesc(product: Product) {
        if (product.desc != null) {
            binding.detailTxtDesc.visibility = View.VISIBLE
            binding.detailTxtDesc.text = "${product.desc}% OFF"
        }else{
            binding.detailTxtDesc.visibility = View.GONE
        }
    }

    private fun setupQuantityButtons(product: Product) {
        var quantidade = 1

        binding.detailQnt.text = quantidade.toString()

        binding.detailBtnN.setOnClickListener {
            if (quantidade > 1) {
                quantidade--
                binding.detailQnt.text = quantidade.toString()
                updateTotalPrice(product, quantidade)
            }
        }

        binding.detailBtnM.setOnClickListener {
            quantidade++
            binding.detailQnt.text = quantidade.toString()
            updateTotalPrice(product, quantidade)
        }
    }

    private fun updateTotalPrice(product: Product, quantity: Int) {
        val totalPrice = calculateTotalPrice(product, quantity)
        binding.detailAmountCart.text = "R$ %.2f".format(totalPrice)
    }

    private fun calculateTotalPrice(product: Product, quantity: Int): Double {
        val originalPrice = product.price
        val discountPercentage = product.desc ?: 0
        val discountedPrice = originalPrice * (1 - (discountPercentage.toDouble() / 100))
        return discountedPrice * quantity
    }

    private fun setupAddToCartButton(product: Product) {
        binding.detailAddCart.setOnClickListener {
            val totalPrice = calculateTotalPrice(product, binding.detailQnt.text.toString().toInt())
            viewModel.addToCart(totalPrice)
        }
    }

}
