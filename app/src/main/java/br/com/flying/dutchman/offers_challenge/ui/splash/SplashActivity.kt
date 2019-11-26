package br.com.flying.dutchman.offers_challenge.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import br.com.flying.dutchman.offers_challenge.ui.offers_list.MainActivity
import br.com.flying.dutchman.offers_challenge.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DURATION = 5000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {

                goToSearchResultsActivity(this)
            },
            SPLASH_DURATION
        )
    }

    private fun goToSearchResultsActivity(activity: Activity) {
        startActivity(Intent(activity, MainActivity::class.java))
        finish()
    }
}
