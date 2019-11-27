package br.com.flying.dutchman.offers_challenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        OfferEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class OffersDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao

}
