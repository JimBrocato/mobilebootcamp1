package com.wcg.mobilebootcamp1_jim.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wcg.mobilebootcamp1_jim.ProductsFragmentDirections
import com.wcg.mobilebootcamp1_jim.R
import com.wcg.mobilebootcamp1_jim.viewmodels.Product


class ProductRecyclerViewAdapter (
    //val navFunction: (Int) -> Unit
    //private val values : List<Product>
    ) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>() {

    //val values : List<Product>
    var values: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
//    var mainView: View? = null
//        set(value) {
//            field = value
//        }

    //var productClickHandler: (Int) -> Int = { id: Int -> Log.e("id", id.toString()) }
    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_product_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.priceView.text = "$" + item.price.toString()
        holder.categoryView.text = item.category
        //holder.imageView.setImageURI(Uri.parse(item.image))
        Picasso.get().load(item.image).into(holder.imageView)
        holder.descriptionView.text = item.description
        //holder.containerView.setOnClickListener { Log.i("Clicked", item.title) }
        //holder.containerView.setOnClickListener { handleClick(item.id) }
    }

    fun handleClick(id: Int) {
        Log.i("Clicked", id.toString())
        Log.i("adapter handle click", id.toString())
        //navFunction(id)
        //val action = ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment()
        //mainView?.findNavController()?.navigate(action)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val priceView: TextView = view.findViewById(R.id.tvPrice)
        val categoryView: TextView = view.findViewById(R.id.tvCategory)
        val imageView: ImageView = view.findViewById(R.id.imgProductDetailImage)
        val descriptionView: TextView = view.findViewById(R.id.tvDescription)
        val containerView: ConstraintLayout = view.findViewById(R.id.product_detail_container)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(values[adapterPosition].id)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

}