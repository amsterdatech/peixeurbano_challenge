package br.com.flying.dutchman.offers_challenge.data.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

@Dao
abstract class OfferDao {
    @Query("SELECT * FROM offers")
    abstract fun getOffers(): Flowable<List<OfferEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(offer: OfferEntity): Completable

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(offers: List<OfferEntity>) {
        offers.map {
            insert(it)
                .subscribeOn(Schedulers.io())
        }
    }

}
