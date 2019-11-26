package br.com.flying.dutchman.offers_challenge.ui

import android.os.Parcel
import android.os.Parcelable

data class Offer(
    val images: List<Image>,
    var imageSelected: Int = 0,
    val title: String,
    val description: String,
    val price: String,
    val fullPrice: String,
    val soldUnits: Int,
    val review: Review,
    val details: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Image),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(Review::class.java.classLoader),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(images)
        parcel.writeInt(imageSelected)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(price)
        parcel.writeString(fullPrice)
        parcel.writeInt(soldUnits)
        parcel.writeParcelable(review, flags)
        parcel.writeString(details)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Offer> {
        override fun createFromParcel(parcel: Parcel): Offer {
            return Offer(parcel)
        }

        override fun newArray(size: Int): Array<Offer?> {
            return arrayOfNulls(size)
        }
    }


}

data class Review(
    val reviewsCount: Int,
    val score: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(reviewsCount)
        parcel.writeDouble(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }

}

data class Image(
    val image: String,
    val original: String,
    val thumb: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(original)
        parcel.writeString(thumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}


