package br.com.flying.dutchman.offers_challenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
class OfferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val shortTitle: String,
    val soldOut: Boolean,
    val salePrice: Double,
    val fullPrice: Double,
    val soldUnits: Int,
    val details: String
)