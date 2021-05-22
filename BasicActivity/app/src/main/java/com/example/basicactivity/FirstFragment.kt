package com.example.basicactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.synnapps.carouselview.CarouselView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.synnapps.carouselview.ImageListener
import com.example.basicactivity.adapters.CarteleraAdapter
import com.example.basicactivity.myobjects.Movie
import com.example.basicactivity.myobjects.MyViewModel
import com.example.basicactivity.myobjects.Utils.Companion.toast
import java.util.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var carousel : CarouselView
    lateinit var recyclerCartelera : RecyclerView

    lateinit var cartelera : ArrayList<Int>

    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carousel = view.findViewById(R.id.carouselViewCartelera)
        recyclerCartelera = view.findViewById(R.id.recyclerCartelera)

        cartelera = ArrayList()
        cartelera.add(R.mipmap.cartelera1)
        cartelera.add(R.mipmap.cartelera2)
        cartelera.add(R.mipmap.cartelera3)
        cartelera.add(R.mipmap.cartelera4)
        cartelera.add(R.mipmap.cartelera5)
        cartelera.add(R.mipmap.cartelera6)

        "Exhibiendo ${cartelera.size} peliculas".toast(view.context)

        val movies = ArrayList<Movie>()
        movies.add( Movie(1,"Kimetsu no Yaiba", R.mipmap.cartelera1, arrayListOf("18:00", "20:00", "22:00")) )
        movies.add( Movie(2,"Godzilla vs Kong", R.mipmap.cartelera2, arrayListOf("13:00", "15:00", "17:00", "19:00", "21:00")) )
        movies.add( Movie(3,"Mortal Kombat", R.mipmap.cartelera3, arrayListOf("21:00", "23:00")) )
        movies.add( Movie(4,"Tenet", R.mipmap.cartelera4, arrayListOf("14:00", "16:00")) )
        movies.add( Movie(5,"The Croods 2", R.mipmap.cartelera5, arrayListOf("13:30", "15:30", "17:30")) )
        movies.add( Movie(6,"The father", R.mipmap.cartelera6, arrayListOf("15:30", "17:30", "19:30")) )

        recyclerCartelera.adapter = object : CarteleraAdapter(view.context, movies, R.layout.recycler_cartelera) {
            override fun verHorarios(movie: Movie) {
                viewModel.setMovie(movie)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
        recyclerCartelera.layoutManager = GridLayoutManager(view.context, 3)

        carousel.pageCount = cartelera.size
        carousel.setImageListener(object : ImageListener {
            override fun setImageForPosition(position: Int, imageView: ImageView?) {
                imageView?.let {
                    it.setImageResource(cartelera[position])
                    it.scaleType = ImageView.ScaleType.FIT_CENTER
                }
            }
        })

    }
}