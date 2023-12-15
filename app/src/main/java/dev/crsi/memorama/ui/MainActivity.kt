package dev.crsi.memorama.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dev.crsi.memorama.adapters.AdapterScore
import dev.crsi.memorama.databinding.ActivityMainBinding
import dev.crsi.memorama.providers.sqlite.querys.ScoreQuery

class MainActivity : AppCompatActivity() {


    private lateinit var scoreQuery: ScoreQuery
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterScore: AdapterScore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        scoreQuery = ScoreQuery(this)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        setData()
        setListener()
    }

    private fun setData() {

        val listScore = scoreQuery.getScores()
        adapterScore = AdapterScore(this, listScore)
        binding.listViewScores.adapter = adapterScore


    }

    private fun setListener() {

        binding.buttonStartgame.setOnClickListener {
            val i = Intent(this, GameActivity::class.java)
            startActivity(i)
        }
    }
}