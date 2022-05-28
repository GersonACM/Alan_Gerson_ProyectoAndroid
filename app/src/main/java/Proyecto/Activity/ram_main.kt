package Proyecto.Activity

import Adaptador.PersonajeAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class ram_main : Fragment() {
    lateinit var miRecycler: RecyclerView
    lateinit var listaPersonajes:ArrayList<Personaje>
    lateinit var adaptador: PersonajeAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista =inflater.inflate(R.layout.fragment_ram_main,container,false)

        listaPersonajes = ArrayList<Personaje>()
        miRecycler= vista.findViewById(R.id.RecyclerPersonajes)
        adaptador = PersonajeAdapter(listaPersonajes)
        miRecycler.adapter= adaptador
        getPersonajes()
        miRecycler.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        return vista
    }




    fun getPersonajes(){
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://rickandmortyapi.com/api/character"
        val objectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { respuesta ->
                val personajesJson = respuesta.getJSONArray("results")

                for (indice in 0..personajesJson.length()-1){
                    val personajeIndJson = personajesJson.getJSONObject(indice)
                    val personaje = Personaje(personajeIndJson.getString("name"),personajeIndJson.getString("image"))
                    listaPersonajes.add(personaje)
                }

                adaptador.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Log.e("PersonajesApi", "Error")
            })
        queue.add(objectRequest)
    }
}
