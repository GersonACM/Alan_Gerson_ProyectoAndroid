package Proyecto.Activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random


class tablas_pract : Fragment() {
    lateinit var tvFactor1: TextView
    lateinit var tvFactor2: TextView
    lateinit var Respuesta: EditText
    lateinit var btnRespuesta: Button
    var factor1:Int = 0
    var factor2:Int = 0
    var producto:Int = 0
    var respuestaCorrecta = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_tablas_pract, container, false)
        initUI()

        btnRespuesta.setOnClickListener {
            var strRespuesta = Respuesta.text.toString()
            if(strRespuesta.equals("")){
                Toast.makeText(this, "Respuesta Vacia", Toast.LENGTH_SHORT).show()

            }else{
                respuestaCorrecta = producto == strRespuesta.toInt()
                generaMultiplicacion()
                val mostrarResultado = Intent(this, ResultadoActivity::class.java)
                mostrarResultado.putExtra("respuesta",respuestaCorrecta)
                startActivity(mostrarResultado)

            }

        }

    }
    fun initUI(){
        tvFactor1 = findViewById(R.id.tvFactor1)
        tvFactor2 = findViewById(R.id.tvFactor2)
        Respuesta = findViewById(R.id.Respuest)
        btnRespuesta = findViewById(R.id.btnResponder)

        generaMultiplicacion()

    }

    fun generaMultiplicacion(){
        factor1 = Random.nextInt(0,10)
        factor2 = Random.nextInt(0,10)
        producto = factor1*factor2
        tvFactor1.text = "$factor1"
        tvFactor2.text = "$factor2"
        Respuesta.text.clear()

    }
        return vista
    }


}