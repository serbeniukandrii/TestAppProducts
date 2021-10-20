package by.com.test.ui

import android.view.View
import by.com.test.data.model.ProductModel
import by.lifetech.test.R
import by.lifetech.test.databinding.ItemProductBinding
import com.bumptech.glide.Glide
import com.xwray.groupie.viewbinding.BindableItem

class ProductItem(
    private val productModel: ProductModel
) : BindableItem<ItemProductBinding>() {
    override fun bind(viewBinding: ItemProductBinding, position: Int) {
        viewBinding.nameTextField.text = productModel.name
        viewBinding.priceTextField.text = productModel.price.toString()
        Glide.with(viewBinding.productPhotoImage)
            .load(productModel.image)
            .into(viewBinding.productPhotoImage)
    }

    override fun getLayout() = R.layout.item_product

    override fun initializeViewBinding(view: View) = ItemProductBinding.bind(view)
}