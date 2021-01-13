package com.wcg.mobilebootcamp1_jim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wcg.mobilebootcamp1_jim.adapters.ProductRecyclerViewAdapter
import com.wcg.mobilebootcamp1_jim.dummy.DummyContent
import com.wcg.mobilebootcamp1_jim.viewmodels.Product
import com.wcg.mobilebootcamp1_jim.viewmodels.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ProductsFragment : Fragment() {

    // https://github.com/google-developer-training/android-kotlin-fundamentals-apps/blob/master/DevBytesRepository/app/src/main/java/com/example/android/devbyteviewer/ui/DevByteFragment.kt

    private val viewModel: ProductListViewModel by viewModels()

    private var viewModelAdapter: ProductRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        viewModelAdapter = ProductRecyclerViewAdapter()
        //(viewModelAdapter as ProductRecyclerViewAdapter).mainView = view
        (viewModelAdapter as ProductRecyclerViewAdapter).onItemClick = { id -> navigateToProductDetail(id) }

        view.findViewById<RecyclerView>(R.id.list).apply {
            adapter = viewModelAdapter
        }

        return view
    }

    fun navigateToProductDetail(id: Int) {
        Log.i("navigate:", id.toString())
        //val productItem = viewModelAdapter?.values?.get(id) // TODO
        var action = ShopFragmentDirections.actionShopFragmentToProductDetailFragment(id)
        findNavController().navigate(action)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.products.observe(viewLifecycleOwner, Observer<List<Product>> { list ->
            list?.apply {
                viewModelAdapter?.values = list
            }
            view.findViewById<TextView>(R.id.tvProductCount).text = viewModelAdapter?.itemCount.toString()
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}