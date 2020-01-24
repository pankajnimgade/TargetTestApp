package com.target.framework.model

import android.os.Parcel
import android.os.Parcelable
import com.target.businesslogic.model.Product

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
class ProductModel(
    _id: String? = null,
    aisle: String? = null,
    description: String? = null,
    guid: String? = null,
    image: String? = null,
    index: Long? = null,
    price: String? = null,
    salePrice: String? = null,
    title: String? = null
) : Product(_id, aisle, description, guid, image, index, price, salePrice, title), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(aisle)
        parcel.writeString(description)
        parcel.writeString(guid)
        parcel.writeString(image)
        parcel.writeLong(index ?: 0)
        parcel.writeString(price)
        parcel.writeString(salePrice)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }

}
fun Product.toParcelable(): ProductModel {
    return ProductModel(
        _id = this._id,
        aisle = this.aisle,
        description = this.description,
        guid = this.guid,
        image = this.image,
        index = this.index,
        price = this.price,
        salePrice = this.salePrice,
        title = this.title
    )
}