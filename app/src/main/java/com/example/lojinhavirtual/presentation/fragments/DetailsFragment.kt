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
    private var quantidade: Int = 1
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resources = requireContext().resources
        val coverResourceId =
            resources.getIdentifier(args.model.coverUrl, "drawable", requireContext().packageName)
        binding.detailProductImg.setImageResource(coverResourceId)

        binding.detailTxtTitle.text = args.model.name
        binding.detailTxtDescription.text = args.model.name
        binding.detailQnt.text = quantidade.toString()
        val originalPrice = args.model.price
        val discountPercentage = args.model.desc
        val discountedPrice: Double

        if (args.model.desc != null) {
            binding.detailTxtDesc.visibility = View.VISIBLE
            binding.detailTxtDesc.text = "$discountPercentage% OFF"
            discountedPrice = originalPrice * (discountPercentage!! / 100.0)
            binding.detailAmountCart.text = "R$ ${String.format("%.2f", originalPrice - discountedPrice)}"
        } else {
            binding.detailTxtDesc.visibility = View.GONE
            binding.detailAmountCart.text = "R$ $originalPrice"
        }

        binding.detailBtnN.setOnClickListener {
            if (quantidade > 1){
                quantidade--
                binding.detailQnt.text = quantidade.toString()
            }
        }
        binding.detailBtnM.setOnClickListener {
            quantidade++
            binding.detailQnt.text = quantidade.toString()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}