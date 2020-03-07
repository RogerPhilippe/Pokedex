package br.com.fiap.phs.pokedex.di

import android.content.Context
import br.com.fiap.phs.pokedex.api.AuthInterceptor
import br.com.fiap.phs.pokedex.api.PokemonService
import br.com.fiap.phs.pokedex.repository.PokemonRepository
import br.com.fiap.phs.pokedex.repository.PokemonRepositoryImpl
import br.com.fiap.phs.pokedex.view.form.FormPokemonViewModel
import br.com.fiap.phs.pokedex.view.list.ListPokemonsAdapter
import br.com.fiap.phs.pokedex.view.list.ListPokemonsViewModel
import br.com.fiap.phs.pokedex.view.splash.SplashViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun createPicassoAuth(context: Context, client: OkHttpClient): Picasso {
    return Picasso.Builder(context)
        .downloader(OkHttp3Downloader(client))
        .build()
}

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokedexdx.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ListPokemonsViewModel(get()) }
    viewModel { FormPokemonViewModel(get()) }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createNetworkClient(get()).create(PokemonService::class.java) }
    single { createOkhttpClientAuth(get()) }
    single { createPicassoAuth(get(), get()) }
}

val viewModule = module {
    factory { ListPokemonsAdapter(get()) }
}