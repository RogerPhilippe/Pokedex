package br.com.fiap.phs.pokedex.view.form

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.phs.pokedex.R
import br.com.fiap.phs.pokedex.model.Pokemon
import br.com.fiap.phs.pokedex.view.ViewState
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_pokemon.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FormPokemonActivity : AppCompatActivity() {

    private val formPokemonViewModel: FormPokemonViewModel by viewModel()
    private val picasso: Picasso by inject()
    lateinit var pokemon : Pokemon
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pokemon)
        setValues()

        formPokemonViewModel.viewState.observe(this, Observer {
            when(it) {
                is ViewState.Failed -> { makeToast(it.throwable.message ?: "Erro desconhecido.") }
                is ViewState.Success -> { makeToast(it.data) }
                is ViewState.Loading -> { }//containerLoading.visibility = View.VISIBLE }
            }
        })

        btSaveForm.setOnClickListener {
            pokemon.attack = sbAttack.progress
            pokemon.defense = sbDefense.progress
            pokemon.velocity = sbVelocity.progress
            pokemon.ps = sbPS.progress
            formPokemonViewModel.updatePokemon(pokemon)
        }
    }

    private fun setValues() {
        pokemon = intent.getParcelableExtra<Pokemon>("POKEMON")
        tvPokemonNameForm.text = pokemon.name

        picasso.load("https://pokedexdx.herokuapp.com${pokemon.imageURL}").into(ivPokemonForm)
        sbAttack.progress = pokemon.attack
        sbDefense.progress = pokemon.defense
        sbPS.progress = pokemon.ps
        sbVelocity.progress = pokemon.velocity
        tvAttackValue.text = pokemon.attack.toString()
        tvDefenseValue.text = pokemon.defense.toString()
        tvPSValue.text = pokemon.ps.toString()
        tvVelocityValue.text = pokemon.velocity.toString()
        setListener(sbAttack, tvAttackValue)
        setListener(sbDefense, tvDefenseValue)
        setListener(sbVelocity, tvVelocityValue)
        setListener(sbPS, tvPSValue)
    }

    private fun setListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser:
            Boolean) {
                textView.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun makeToast(msg: String) {
        //containerLoading.visibility = View.GONE
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
