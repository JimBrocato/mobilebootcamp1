package com.wcg.mobilebootcamp1_jim

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.wcg.mobilebootcamp1_jim.viewmodels.Product
import com.wcg.mobilebootcamp1_jim.viewmodels.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM_PRODUCT_ID = "productId"


/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var productId: Int = 0  //  no bueno
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getInt(ARG_PARAM_PRODUCT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProduct(productId).observe(viewLifecycleOwner, Observer<Product> { item ->
            view.findViewById<TextView>(R.id.tvTitle).text = item.title
            view.findViewById<TextView>(R.id.tvCategory).text = item.category
            view.findViewById<TextView>(R.id.tvPrice).text = "$" + item.price.toString()
            view.findViewById<TextView>(R.id.tvDescription).text = item.description
            val imageView: ImageView = view.findViewById(R.id.imgProductDetailImage)
            Picasso.get().load(item.image).into(imageView)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ProductDetailFragment.
         */
        @JvmStatic
        fun newInstance(productId: Int) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM_PRODUCT_ID, productId)
                }
            }
    }
}