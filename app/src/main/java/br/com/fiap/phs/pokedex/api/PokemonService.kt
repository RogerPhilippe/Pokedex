package br.com.fiap.phs.pokedex.api

import br.com.fiap.phs.pokedex.model.HealthResponse
import br.com.fiap.phs.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("/api/pokemon/health")
    fun checkHealth(): Call<HealthResponse>

    @GET("/api/pokemon")
    fun getPokemons(
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Call<PokemonResponse>

}