package by.com.test.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ProductsResponse(
    @SerialName("products")
    val products: List<ProductModel>
)