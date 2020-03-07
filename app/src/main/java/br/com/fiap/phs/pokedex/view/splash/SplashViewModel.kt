package br.com.fiap.phs.pokedex.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.phs.pokedex.repository.PokemonRepository

class SplashViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val messageError: MutableLiveData<String> = MutableLiveData()
    fun checkHealth() {
        pokemonRepository.checkHealth(
            onComplete = {
                messageError.value = ""
            },
            onError = {
                messageError.value = it?.message
            }
        )
    }
}