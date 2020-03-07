package br.com.fiap.phs.pokedex.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.phs.pokedex.model.Pokemon
import br.com.fiap.phs.pokedex.repository.PokemonRepository
import br.com.fiap.phs.pokedex.view.ViewState

class ListPokemonsViewModel (private val pokemonRepository: PokemonRepository) : ViewModel() {

    val viewState: MutableLiveData<ViewState<List<Pokemon>>> = MutableLiveData()

    fun getPokemons() {

        viewState.value = ViewState.Loading

        pokemonRepository.getPokemons(
            150,
            "number,asc",
            { viewState.value = ViewState.Success(it) },
            { viewState.value = ViewState.Failed(it) }
        )
    }
}