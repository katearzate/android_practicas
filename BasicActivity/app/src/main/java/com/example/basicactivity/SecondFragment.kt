package com.example.basicactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.basicactivity.myobjects.Compra
import com.example.basicactivity.myobjects.Movie
import com.example.basicactivity.myobjects.MyViewModel


class SecondFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val img = view.findViewById<ImageView>(R.id.imgHorarioPelicula)
        val nombrePelicula = view.findViewById<TextView>(R.id.tvHorarioPelicula)
        val spinnerHorarios = view.findViewById<Spinner>(R.id.spinnerHorarioPelicula)

        viewModel.getMovie()?.observe(requireActivity(), object : Observer<Movie?> {
            override fun onChanged(m: Movie?) {
                m?.let {
                    //Toast.makeText(view.context,"${it.name}",Toast.LENGTH_LONG).show()

                    img.setImageResource(it.imgCarusel)
                    nombrePelicula.setText(it.name)
                    spinnerHorarios.adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, it.horarios)

                    spinnerHorarios.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    }

                    /*
                    list.setOnItemClickListener { adapterView, view, i, l ->
                        val compra = Compra(it.horarios[i], it, 0)
                        viewModel.setCompra(compra)

                        findNavController().navigate(R.id.action_SecondFragment_to_myFragment2)
                    }*/
                }
            }
        })

    }
}