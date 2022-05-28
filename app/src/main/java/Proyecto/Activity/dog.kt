package Proyecto.Activity

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class dog : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var adapter: DogAdapter
    private val DogImages= mutableListOf<String>()
    lateinit var dogRecycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista = inflater.inflate(R.layout.fragment_dog, container, false)
        vista.findViewById<SearchView>(R.id.svDogs).setOnQueryTextListener(this)
        dogRecycler=vista.findViewById(R.id.rvDogs)
        initRecyclerView()
        return vista
    }
    private fun initRecyclerView() {
        adapter = DogAdapter(DogImages)
        dogRecycler.layoutManager=LinearLayoutManager(requireContext())
        dogRecycler.adapter=adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            requireActivity().runOnUiThread {
                if(call.isSuccessful){
                    val images = puppies?.images ?: emptyList()
                    DogImages.clear()
                    DogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }


        }

    }
    private fun showError() {
        Toast.makeText(requireContext(),"Estamos teniendo  un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchByName(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean{
        return true
    }


}