package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodels.ShoeStoreViewModel

class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<ShoeStoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            saveButtonClick()
        }

        binding.cancelButton.setOnClickListener {
            viewModel.resetNewShoe()
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }

    private fun saveButtonClick() {
        if (viewModel.saveNewShoe(binding.nameText.text.toString(),
                binding.sizeText.text.toString(),
                binding.companyText.text.toString(),
                binding.descriptionText.text.toString())
            )
         {
             findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
         } else {
            Snackbar.make(binding.root, getString(R.string.new_shoe_error), Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}