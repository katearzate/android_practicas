package com.example.basicactivity

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basicactivity.myobjects.Movie
import com.example.basicactivity.myobjects.MyViewModel


class CarteleraSecondFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_cartelera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val img = view.findViewById<ImageView>(R.id.imgHorarioPelicula)
        val nombrePelicula = view.findViewById<TextView>(R.id.tvHorarioPelicula)
        val spinnerHorarios = view.findViewById<Spinner>(R.id.spinnerHorarioPelicula)

        val spinnerCantidad = view.findViewById<Spinner>(R.id.spinnerCantidad)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalCompra)
        val btnConfirmar = view.findViewById<Button>(R.id.btnConfirmar)

        viewModel.getMovie()?.observe(requireActivity(), object : Observer<Movie?> {
            override fun onChanged(m: Movie?) {
                m?.let {
                    //Toast.makeText(view.context,"${it.name}",Toast.LENGTH_LONG).show()

                    img.setImageResource(it.imgCarusel)
                    nombrePelicula.setText(it.name)
                    spinnerHorarios.adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, it.horarios)

                    spinnerHorarios.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    }

                    /*
                    list.setOnItemClickListener { adapterView, view, i, l ->
                        val compra = Compra(it.horarios[i], it, 0)
                        viewModel.setCompra(compra)

                        findNavController().navigate(R.id.action_SecondFragment_to_myFragment2)
                    }*/

                    var noBoletos : Int = 0
                    var total : Int = 0
                    spinnerCantidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                            noBoletos = (index+1)
                            total = noBoletos*50
                            tvTotal.setText("TOTAL: ${total}")
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    }

                    btnConfirmar.setOnClickListener {
                        //Toast.makeText(view.context,"Confirmación de compra",Toast.LENGTH_LONG).show()

                        val builder = AlertDialog.Builder(activity!!)
                        builder.setMessage("Compra de ${noBoletos} para ${nombrePelicula.text.toString()} realizada")
                        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener{
                            dialog, it ->
                            dialog.dismiss()
                        })
                        val alert = builder.create()
                        alert.setTitle("Confirmación de compra")
                        alert.show()
                    }


                }
            }
        })
    }
}