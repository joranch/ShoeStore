package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemListShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeStoreViewModel
import kotlinx.android.synthetic.main.item_list_shoe.view.*

class ShoeListFragment : Fragment() {
    private val viewModel by activityViewModels<ShoeStoreViewModel>()
    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.shoeList.observe(viewLifecycleOwner, { items ->
            binding.shoeList.removeAllViewsInLayout()

            items.forEach {
                val shoeView = createShowItemView(it)
                binding.shoeList.addView(shoeView)
            }
        })

        binding.addNewButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
    }

    private fun createShowItemView(shoe: Shoe): View {
        val shoeBinding = ItemListShoeBinding.inflate(layoutInflater)
        shoeBinding.shoeName.text = shoe.name
        shoeBinding.shoeBrand.text = shoe.company

        return shoeBinding.root
    }

}