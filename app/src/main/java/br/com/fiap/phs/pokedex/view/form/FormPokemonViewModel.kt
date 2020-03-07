package br.com.fiap.phs.pokedex.view.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.phs.pokedex.model.Pokemon
import br.com.fiap.phs.pokedex.repository.PokemonRepository
import br.com.fiap.phs.pokedex.view.ViewState

class FormPokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    val viewState: MutableLiveData<ViewState<String>> = MutableLiveData()

    fun updatePokemon(pokemon: Pokemon) {

        viewState.value = ViewState.Loading

        pokemonRepository.updatePokemon(
            pokemon = pokemon,
            onComplete = {
                viewState.value = ViewState.Success("Dados atualizados com sucesso")
            },
            onError = {
                viewState.value = ViewState.Failed(it)
            }
        )
    }
}


/**
    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

    fun updatePokemon(pokemon: Pokemon) {
    isLoading.value = true
    pokemonRepository.updatePokemon(
    pokemon = pokemon,
    onComplete = {
    isLoading.value = false
    messageResponse.value = "Dados atualizados com sucesso"
    },
    onError = {
    isLoading.value = false
    messageResponse.value = it.message
    }
    )
    }
 */