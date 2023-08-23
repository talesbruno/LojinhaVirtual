package com.example.lojinhavirtual.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.lojinhavirtual.databinding.DetailsFragmentBinding
import com.example.lojinhavirtual.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailProductImg.setImageResource(args.model.coverUrl)
        binding.detailTxtDesc.text = args.model.desc.toString()
        binding.detailTxtTitle.text = args.model.name
        binding.detailTxtDescription.text = args.model.name
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}