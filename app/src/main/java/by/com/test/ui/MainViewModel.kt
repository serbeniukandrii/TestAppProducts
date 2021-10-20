package by.com.test.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.com.test.data.ProductsRepository
import by.com.test.data.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductModel>?>(null)
    val products = _products.asStateFlow()

    @ExperimentalSerializationApi
    fun loadProducts() {
        viewModelScope.launch {
            val productsResponse = productsRepository.getProducts()
            _products.value = productsResponse.products
        }
    }

}