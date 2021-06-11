package com.example.plataformasge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plataformasge.R
import com.example.plataformasge.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private var binding: FragmentScheduleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        val spinnerCantidad = view.findViewById<Spinner>(R.id.spinnerCantidad)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalCompra)
        val btnConfirmar = view.findViewById<Button>(R.id.btnConfirmar)

        spinnerCantidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                tvTotal.setText("TOTAL: ${(index+1)*50.0}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
         */
    }
}