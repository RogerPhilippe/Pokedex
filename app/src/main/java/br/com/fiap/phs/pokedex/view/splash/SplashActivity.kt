package br.com.fiap.phs.pokedex.view.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.phs.pokedex.R
import br.com.fiap.phs.pokedex.view.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel.checkHealth()
        splashViewModel.messageError.observe(this, Observer {
            if (it == "") {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}
