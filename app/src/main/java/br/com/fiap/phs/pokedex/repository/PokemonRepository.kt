package br.com.fiap.phs.pokedex.repository

import br.com.fiap.phs.pokedex.model.Pokemon

interface PokemonRepository {

    fun checkHealth(
        onComplete:() -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

}