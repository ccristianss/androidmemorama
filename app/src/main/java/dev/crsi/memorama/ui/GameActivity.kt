package dev.crsi.memorama.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.crsi.memorama.R
import dev.crsi.memorama.databinding.ActivityGameBinding
import dev.crsi.memorama.models.Score
import dev.crsi.memorama.providers.SharedPreferenceManager
import dev.crsi.memorama.providers.sqlite.querys.ScoreQuery
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var scoreQuery: ScoreQuery
    private lateinit var sharedPref: SharedPreferenceManager

    private lateinit var puntaje2: TextView
    private var pares = 0
    private var hI: Long = 0
    lateinit var mDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(LayoutInflater.from(this))
        scoreQuery = ScoreQuery(this)
        sharedPref = SharedPreferenceManager(this)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.jugarAhora.setOnClickListener {
            if (binding.usuario.text.isNotEmpty()) {

                sharedPref.savePref("usuario", binding.usuario.text.toString())

                ShowGame(binding.usuario.text.toString())
            } else {
                binding.usuario.requestFocus()
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun ShowGame(usuario: String) {
        val letras = listOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H"
        )
        val cardaleatorio = ArrayList<Int>()
        for (i in 0..15) {
            while (true) {
                val card = Random.nextInt(0, 16)
                if (!cardaleatorio.contains(card)) {
                    cardaleatorio.add(card)
                    break
                }
            }
        }
        var primerclick = true
        var cartasseleccionadas = 0
        var posicionseleccionada1 = 0
        var posicionseleccionada2: Int
        var letra0: String = ""
        var letra1: String = ""
        var imagen0: Int
        var imagen1: Int
        val b = AlertDialog.Builder(this)
        val mview = LayoutInflater.from(this).inflate(R.layout.dialog_juego, null)
        puntaje2 = mview.findViewById<TextView>(R.id.tiempo)

        val cards = listOf(
            mview.findViewById<ImageView>(R.id.card01) as ImageView,
            mview.findViewById<ImageView>(R.id.card02) as ImageView,
            mview.findViewById<ImageView>(R.id.card03) as ImageView,
            mview.findViewById<ImageView>(R.id.card04) as ImageView,
            mview.findViewById<ImageView>(R.id.card05) as ImageView,
            mview.findViewById<ImageView>(R.id.card06) as ImageView,
            mview.findViewById<ImageView>(R.id.card07) as ImageView,
            mview.findViewById<ImageView>(R.id.card08) as ImageView,
            mview.findViewById<ImageView>(R.id.card09) as ImageView,
            mview.findViewById<ImageView>(R.id.card10) as ImageView,
            mview.findViewById<ImageView>(R.id.card11) as ImageView,
            mview.findViewById<ImageView>(R.id.card12) as ImageView,
            mview.findViewById<ImageView>(R.id.card13) as ImageView,
            mview.findViewById<ImageView>(R.id.card14) as ImageView,
            mview.findViewById<ImageView>(R.id.card15) as ImageView,
            mview.findViewById<ImageView>(R.id.card16) as ImageView
        )
        for (i in cards.indices) {
            val carta = cards[i]
            carta.setOnClickListener {
                val time2 = (SystemClock.elapsedRealtime() - hI).toInt()
                puntaje2.text = "$time2"
                if (primerclick) {
                    hI = SystemClock.elapsedRealtime()
                    primerclick = false
                }
                if (cartasseleccionadas == 0) {
                    cartasseleccionadas += 1
                    posicionseleccionada1 = i
                    val pt0 = cardaleatorio[i]
                    letra0 = letras[pt0]
                    imagen0 = obtenerImagen(letra0)
                    carta.setImageResource(imagen0)
                    carta.isEnabled = false
                } else if (cartasseleccionadas == 1) {
                    cartasseleccionadas += 1
                    posicionseleccionada2 = i
                    val pt1 = cardaleatorio[i]
                    letra1 = letras[pt1]
                    imagen1 = obtenerImagen(letra1)
                    carta.setImageResource(imagen1)
                    carta.isEnabled = false
                    val espera = Runnable {
                        if (letra0 == letra1) {
                            pares += 1
                            if (pares == 8) {
                                mDialog.dismiss()
                                val time = (SystemClock.elapsedRealtime() - hI).toInt()
                                val mPuntaje = Score()
                                mPuntaje.username = usuario
                                mPuntaje.score = time


                                val status = scoreQuery.addScore(mPuntaje)
                                if (status > -1) {
                                    binding.puntaje.text = "Tu puntaje es: $time"
                                }
                            }
                        } else {
                            cards[posicionseleccionada1].setImageResource(R.drawable.splash)
                            cards[posicionseleccionada1].isEnabled = true
                            cards[posicionseleccionada2].setImageResource(R.drawable.splash)
                            cards[posicionseleccionada2].isEnabled = true
                        }
                        imagen0 = 0
                        imagen1 = 0
                        cartasseleccionadas = 0
                    }

                    val retardo: Long = (5 * 100)
                    Handler().postDelayed(
                        espera, retardo
                    )
                }
            }
        }
        b.setView(mview)
        val dialog: AlertDialog = b.create()
        mDialog = dialog
        mDialog.setCancelable(false)
        mDialog.show()
    }

    private fun obtenerImagen(i: String): Int {
        var imagen: Int = R.drawable.splash
        when (i) {
            "A" -> {
                imagen = R.drawable.im1
            }

            "B" -> {
                imagen = R.drawable.im2
            }

            "C" -> {
                imagen = R.drawable.im3
            }

            "D" -> {
                imagen = R.drawable.im4
            }

            "E" -> {
                imagen = R.drawable.im5
            }

            "F" -> {
                imagen = R.drawable.im6
            }

            "G" -> {
                imagen = R.drawable.im7
            }

            "H" -> {
                imagen = R.drawable.im8
            }
        }
        return imagen
    }

    override fun onBackPressed() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
        super.onBackPressed()
    }
}