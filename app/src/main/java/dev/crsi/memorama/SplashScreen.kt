package dev.crsi.memorama

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dev.crsi.memorama.databinding.ActivitySplashScreenBinding
import dev.crsi.memorama.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val timeSplash = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        nextActivity()
    }

    private fun nextActivity() =
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }, timeSplash)
        }
}