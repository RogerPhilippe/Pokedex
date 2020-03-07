package br.com.fiap.phs.pokedex.repository

interface PokemonRepository {
    fun checkHealth(
        onComplete:() -> Unit,
        onError: (Throwable?) -> Unit
    )
}