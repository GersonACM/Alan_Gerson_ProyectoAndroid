package Proyecto.Activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class tablas_main : Fragment() {


    lateinit var btnPracticar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista = inflater.inflate(R.layout.fragment_tablas_main, container, false)

        btnPracticar = vista.findViewById(R.id.btnPracticar)
        btnPracticar.setOnClickListener {
            findNavController().navigate(R.id.action_tablas_main2_to_tablas_pract)
        }

        return vista
    }


}