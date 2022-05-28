package Proyecto.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView

class tablas_resultado : Fragment() {

    lateinit var animView: LottieAnimationView
    lateinit var btnRegresar:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_tablas_resultado, container, false)
        btnRegresar = vista.findViewById(R.id.btnRegresar)
        animView = vista.findViewById(R.id.animation_view)
        btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_tablas_resultado_to_tablas_pract)

        }

        if (respuestaCorrecta) {
            animView.setAnimation(R.raw.correctanimation)
        } else {
            animView.setAnimation(R.raw.sadface)
        }


        return vista
    }

}




