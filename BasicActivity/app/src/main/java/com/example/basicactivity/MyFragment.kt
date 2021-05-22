package com.example.basicactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MyFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerCantidad = view.findViewById<Spinner>(R.id.spinnerCantidad)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalCompra)
        val btnConfirmar = view.findViewById<Button>(R.id.btnConfirmar)

        spinnerCantidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                tvTotal.setText("TOTAL: ${(index+1)*50.0}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

}