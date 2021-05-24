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
import com.example.basicactivity.myobjects.Compra
import com.example.basicactivity.myobjects.DatabaseManager
import com.example.basicactivity.myobjects.Movie
import com.example.basicactivity.myobjects.MyViewModel
import java.lang.Exception


class CarteleraSecondFragment : Fragment() {

    private lateinit var viewModel: MyViewModel
    lateinit var dbManager: DatabaseManager

    private var horarioSeleccionado : String = ""
    private var noBoletosSeleccionado : Int = 0
    private var imgPelicula : Int = 0

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

        dbManager = DatabaseManager(activity, "DBpeliculas", null, 1)

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
                    imgPelicula = it.imgCarusel
                    img.setImageResource(it.imgCarusel)
                    nombrePelicula.setText(it.name)
                    spinnerHorarios.adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, it.horarios)

                    spinnerHorarios.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                            horarioSeleccionado = p0?.getItemAtPosition(index).toString()
                            //Toast.makeText(view.context,"hora : ${horarioSeleccionado}",Toast.LENGTH_LONG).show()
                        }
                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    }

                    /*
                    list.setOnItemClickListener { adapterView, view, i, l ->
                        val compra = Compra(it.horarios[i], it, 0)
                        viewModel.setCompra(compra)
                        findNavController().navigate(R.id.action_CarteleraSecondFragment_to_myFragment2)
                    }*/

                    var noBoletos : Int = 0
                    var total : Int = 0
                    spinnerCantidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                            noBoletosSeleccionado = p0?.getItemAtPosition(index).toString().toInt()
                            noBoletos = (index+1)
                            total = noBoletos*50
                            tvTotal.setText("TOTAL: ${total}")
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    }

                    btnConfirmar.setOnClickListener {

                        val builder = AlertDialog.Builder(activity!!)
                        builder.setMessage("¿Desea comprar ${noBoletos} boleto(s) para ${nombrePelicula.text.toString()} a las ${horarioSeleccionado}?")
                        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener{
                            dialog, it ->
                            val compra = Compra("${horarioSeleccionado}",
                                "${nombrePelicula.text}",
                                noBoletosSeleccionado,
                                total,
                                imgPelicula)
                            try {
                                dbManager.add(compra)
                                Toast.makeText(view.context,"Compra realizada",Toast.LENGTH_LONG).show()
                            }catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(view.context, "Error al registrar compra!", Toast.LENGTH_LONG).show()
                            }
                            dialog.dismiss()
                        })
                        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener{
                            dialog, it ->
                            Toast.makeText(view.context, "Compra cancelada!", Toast.LENGTH_LONG).show()
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