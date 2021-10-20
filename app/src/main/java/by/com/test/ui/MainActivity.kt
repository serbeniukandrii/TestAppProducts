package by.com.test.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.com.test.data.model.ProductModel
import by.lifetech.test.R
import by.lifetech.test.databinding.ActivityMainBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.ExperimentalSerializationApi


@AndroidEntryPoint
class MainActivity : AppCompatActivity()  {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private var productsAdapter: GroupieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.products.collect {
                showProducts(it ?: emptyList())
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.loadProducts()
        }
        initUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        productsAdapter = null
        _binding = null
    }

    private fun initUi() {
        productsAdapter = GroupieAdapter()
        binding.productsList.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = productsAdapter
        }
    }

    private fun showProducts(products: List<ProductModel>) {
        productsAdapter?.addAll(products.map { ProductItem(it) })
    }

}
