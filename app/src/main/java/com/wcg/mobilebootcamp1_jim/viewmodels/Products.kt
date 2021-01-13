package com.wcg.mobilebootcamp1_jim.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wcg.mobilebootcamp1_jim.repository.ProductRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

// https://developer.android.com/topic/libraries/architecture/viewmodel
// https://developer.android.com/codelabs/kotlin-android-training-view-model?index=..%2F..android-kotlin-fundamentals#5

@ActivityScoped
class ProductListViewModel @ViewModelInject constructor(
    //savedStateHandle: SavedStateHandle,
    productRepository: ProductRepository
) : ViewModel() {
    val products : LiveData<List<Product>> = productRepository.getProducts()
}

@ActivityScoped
class ProductViewModel @ViewModelInject constructor(
        val productRepository: ProductRepository
) : ViewModel() {
    //Could reuse the above model, but doing this to exercise the pattern
    fun getProduct(id: Int) : LiveData<Product> {
        return productRepository.getProduct(id)
    }
}

data class Product(
    val id: Int,
    val title: String,
    val price: Number,
    val category: String,
    val description: String,
    val image: String,
)