package br.com.fiap.phs.pokedex.api

import br.com.fiap.phs.pokedex.model.HealthResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("/api/pokemon/health")
    fun checkHealth(): Call<HealthResponse>
}