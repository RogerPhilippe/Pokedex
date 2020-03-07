package br.com.fiap.phs.pokedex.view.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fiap.phs.pokedex.R
import br.com.fiap.phs.pokedex.view.ViewState
import br.com.fiap.phs.pokedex.view.form.FormPokemonActivity
import kotlinx.android.synthetic.main.activity_list_pokemons.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListPokemonsActivity : AppCompatActivity() {

    private val listaPokemonsViewModel: ListPokemonsViewModel by viewModel()

    private val adapter: ListPokemonsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemons)

        rvPokemons.adapter = adapter
        rvPokemons.layoutManager = GridLayoutManager(this, 3)

        this.rvListener()

        listaPokemonsViewModel.getPokemons()

        listaPokemonsViewModel.viewState.observe(this, Observer {
            when(it) {
                is ViewState.Loading -> { containerLoading.visibility = View.VISIBLE }
                is ViewState.Failed -> {
                    containerLoading.visibility = View.GONE
                    val message = it.throwable.message
                    if (message != "") Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
                is ViewState.Success -> {
                    containerLoading.visibility = View.GONE
                    adapter.setItems(it.data)
                }
            }
        })
    }

    private fun rvListener() {

        adapter.setOnClickListener { item ->
            val telaDeDetalhe = Intent(this, FormPokemonActivity::class.java)
            telaDeDetalhe.putExtra("POKEMON", item)
            startActivity(telaDeDetalhe)
        }
    }

}
