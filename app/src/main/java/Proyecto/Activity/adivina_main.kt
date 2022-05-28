package Proyecto.Activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import kotlin.random.Random


class adivina_main : Fragment() {


    lateinit var btnIniciar: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_adivina_main, container, false)
        btnIniciar = vista.findViewById(R.id.btnComenzar)
        btnIniciar.setOnClickListener {
            findNavController().navigate(R.id.adivina_main)
        }
        return vista
    }
}


