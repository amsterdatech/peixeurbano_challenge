package br.com.flying.dutchman.offers_challenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import br.com.flying.dutchman.data.remote.OfferRemoteEntityMapper
import br.com.flying.dutchman.offers_challenge.BuildConfig
import br.com.flying.dutchman.offers_challenge.data.OfferRepository
import br.com.flying.dutchman.offers_challenge.data.local.OfferDao
import br.com.flying.dutchman.offers_challenge.data.local.OffersDatabase
import br.com.flying.dutchman.offers_challenge.data.network.OfferApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//    val viewModelModule = module {
//        single { UserViewModel(get()) }
//    }


val apiModule = module {
    fun provideOfferApi(retrofit: Retrofit): OfferApi {
        return retrofit.create(OfferApi::class.java)
    }

    single { provideOfferApi(get()) }
}

val netModule = module {
    fun provideTimeout(): Long = 30

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cacheInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .connectTimeout(provideTimeout(), TimeUnit.SECONDS)
            .readTimeout(provideTimeout(), TimeUnit.SECONDS)
            .writeTimeout(provideTimeout(), TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OfferApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }


    fun provideHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )


    fun provideCacheControlInterceptor(context: Context) = Interceptor { chain ->

        val request = chain.request()
        val response = chain.proceed(request)

        if (response.cacheResponse() != null && response.cacheControl().isPublic) {
            response

        } else {
            response
                .newBuilder()
                .headers(response.headers())
                .build()
        }
    }

    single { provideHttpLogging() }
    single { provideCacheControlInterceptor(androidContext()) }
    single { provideCache(androidApplication()) }
    single { provideHttpClient(get(), get(), get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}

val databaseModule = module {

    fun provideDatabase(application: Application): OffersDatabase {
        return Room.databaseBuilder(application, OffersDatabase::class.java, "offers.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: OffersDatabase): OfferDao {
        return database.offerDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}


val repositoryModule = module {

    fun provideRemoteMapper(): OfferRemoteEntityMapper {
        return OfferRemoteEntityMapper()
    }

    fun provideUserRepository(
        api: OfferApi,
        dao: OfferDao,
        remoteMapper: OfferRemoteEntityMapper
    ): OfferRepository {
        return OfferRepository(api, dao, remoteMapper)
    }

    single { provideRemoteMapper() }
    single { provideUserRepository(get(), get(), get()) }
}


