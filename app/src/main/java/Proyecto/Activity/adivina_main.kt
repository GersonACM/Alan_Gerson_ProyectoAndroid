package Proyecto.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class adivina_main : Fragment() {
    lateinit var btnIniciar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_adivina_main, container, false)
        btnIniciar=vista.findViewById(R.id.btnComenzar)
        btnIniciar.setOnClickListener {
            findNavController().navigate(R.id.action_adivina_main_to_adivina_resultado)
        }
        return vista
        }

}