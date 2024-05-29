package com.example.core.di

import androidx.room.Room
import com.example.core.BuildConfig
import com.example.core.data.MovieRepository
import com.example.core.data.local.LocalDataSource
import com.example.core.data.local.room.DatabaseTransactionHelper
import com.example.core.data.local.room.MovieDatabase
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.repository.IMovieRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    // Provide MovieDao as a singleton
    factory { get<MovieDatabase>().movieDao() }
    // Provide RemoteKeysDao as a singleton
    factory { get<MovieDatabase>().remoteKeysDao() }

    factory { get<MovieDatabase>().movieFavoriteDao() }

    factory { DatabaseTransactionHelper(get()) }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("example".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }


}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/5VLcahb6x4EvvFrCF2TePZulWqrLHS2jCg9Ywv6JHog=")
            .add(hostname, "sha256/vxRon/El5KuI4vx5ey1DgmsYmRY0nDd5Cg4GfJ8S+bg=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.KEY)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val remoteModule = module{
    single { LocalDataSource(get(),get(), get())}
    single { RemoteDataSource(get()) }
    single<IMovieRepository>{ MovieRepository(get(),get(),get(),get()) }
}

