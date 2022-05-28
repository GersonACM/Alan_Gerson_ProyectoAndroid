package Proyecto.Activity

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlin.random.Random


class adivina_resultado: Fragment() {
    lateinit var musicaFondo: MediaPlayer
    lateinit var respuestaUsuario: EditText
    lateinit var btnRespuesta: Button
    lateinit var sonidoRespuestaCorrecta: MediaPlayer
    lateinit var sonidoRespuestaIncorrecta: MediaPlayer
    var numeroGenerado = 0
    var numeroUsuario = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_adivina_main, container, false)

         fun onStop() {
            super.onStop()
            musicaFondo.release()
            sonidoRespuestaCorrecta.release()
            sonidoRespuestaIncorrecta.release()
        }

        fun reproduceMusica() {
            musicaFondo.isLooping = true
            musicaFondo.setVolume(0.5f, 0.5f)
            musicaFondo.start() // no need to call prepare(); create() does that for you
        }

        fun sonidoCorrecto() {
            sonidoRespuestaCorrecta.start()
        }

        fun sonidoIncorrecto() {
            sonidoRespuestaIncorrecta.start()
        }

        fun initUI() {
            btnRespuesta = vista.findViewById(R.id.btnComprobar)
            respuestaUsuario = vista.findViewById(R.id.etEntradaUsuario)
        }

        fun generaNumero() {
            numeroGenerado = Random.nextInt(1, 7)

        }

        initUI()
        musicaFondo = MediaPlayer.create(requireContext(), R.raw.fondo)
        sonidoRespuestaCorrecta = MediaPlayer.create(requireContext(), R.raw.correcto)
        sonidoRespuestaIncorrecta = MediaPlayer.create(requireContext(), R.raw.error)
        reproduceMusica()
        generaNumero()

        btnRespuesta.setOnClickListener {
            val respuesta = respuestaUsuario.text.toString()
            if (respuesta.equals("")) {
                Toast.makeText(requireContext(), "Ingrese un valor", Toast.LENGTH_LONG).show()
                sonidoIncorrecto()
            } else {
                numeroUsuario = respuesta.toInt()
                if (numeroGenerado == numeroUsuario) {
                    sonidoCorrecto()
                    Toast.makeText(requireContext(), "Advininaste el numero", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    sonidoIncorrecto()
                    Toast.makeText(
                        requireContext(),
                        "El numero era $numeroGenerado ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                generaNumero()
                findNavController().navigate(R.id.action_adivina_main_to_adivina_resultado)
            }
        }
        return vista
    }


}


