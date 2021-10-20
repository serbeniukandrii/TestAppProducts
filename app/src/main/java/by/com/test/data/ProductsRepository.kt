package by.com.test.data

import android.content.res.AssetManager
import android.util.Log
import by.com.test.data.model.ProductsResponse
import by.com.test.utils.AssetsManager
import by.com.test.utils.Const
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModuleBuilder
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProductsRepository @Inject constructor(
    private val assetManager: AssetsManager,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) {

    @ExperimentalSerializationApi
    suspend fun getProducts() : ProductsResponse = withContext(dispatcher) {
        val data = assetManager.getAsset("products_list.json")

        val format = Json {
            isLenient = true
        }

        Log.d(Const.APP_TAG, data)
        return@withContext format.decodeFromString(data)
    }

}