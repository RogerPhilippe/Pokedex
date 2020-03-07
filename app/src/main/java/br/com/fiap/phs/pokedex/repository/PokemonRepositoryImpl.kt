package br.com.fiap.phs.pokedex.repository

import br.com.fiap.phs.pokedex.api.PokemonService
import br.com.fiap.phs.pokedex.model.HealthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(private var pokemonService: PokemonService) :
    PokemonRepository {
    override fun checkHealth(onComplete: () -> Unit, onError: (Throwable?) -> Unit) {
        pokemonService.checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<HealthResponse>, response:
                Response<HealthResponse>
                ) {
                    onComplete()
                }
            })
    }
}