package br.com.fiap.phs.pokedex.view.main

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.phs.pokedex.R
import br.com.fiap.phs.pokedex.view.scan.ScanActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btPokedex.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
        btPokemonList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        btClose.setOnClickListener {
            finish()
        }
    }
}
