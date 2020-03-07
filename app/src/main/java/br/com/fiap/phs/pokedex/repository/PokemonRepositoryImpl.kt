package br.com.fiap.phs.pokedex.repository

import br.com.fiap.phs.pokedex.api.PokemonService
import br.com.fiap.phs.pokedex.model.HealthResponse
import br.com.fiap.phs.pokedex.model.Pokemon
import br.com.fiap.phs.pokedex.model.PokemonResponse
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

    override fun getPokemons(
        size: Int, sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        pokemonService.getPokemons(size, sort)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<PokemonResponse>, response:
                Response<PokemonResponse>) {
                    if (response.isSuccessful) {
                        onComplete(response.body()?.content)
                    } else {
                        onError(Throwable("Não foi possível carregar os Pokémons"))
                    }
                }
            })
    }

}