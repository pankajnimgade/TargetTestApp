package com.target.dealbrowserpoc.dealbrowser.view.fragments


import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.api.load
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDealDetailsBinding
import com.target.framework.model.ProductModel

/**
 * A simple [Fragment] subclass.
 */
class DealDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDealDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveProductModelFromBundle()
    }

    private fun retrieveProductModelFromBundle() {
        arguments?.let { it ->
            val dealDetailsFragmentArgs = DealDetailsFragmentArgs.fromBundle(it)
            dealDetailsFragmentArgs.let { args ->
                setUpView(args.productModel)
            }
        }
    }

    private fun setUpView(productModel: ProductModel?) {
        productModel?.let {
            binding.apply {
                imageView.load(it.image) {
                    placeholder(R.drawable.ic_loading)
                }

                newPriceTextView.text = it.salePrice ?: it.price ?: "$0"
                if (!it.salePrice.isNullOrBlank() && !it.price.isNullOrBlank()) {
                    setStrickenPrice(it.price)
                }

                productTitleTextView.text = it.title
                productDescriptionTextView.text = it.description
            }
        }

    }

    private fun setStrickenPrice(price: String?) {
        price?.let {
            binding.previousPriceTextView.setText("Reg:  $it", TextView.BufferType.SPANNABLE)
            val spannable = binding.previousPriceTextView.text as Spannable
            spannable.setSpan(
                StrikethroughSpan(),
                "Reg:  ".length,
                "Reg:  $it".length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}