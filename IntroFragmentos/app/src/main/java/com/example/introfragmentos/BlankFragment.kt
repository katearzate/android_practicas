package com.example.introfragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

class BlankFragment : Fragment() {
    
    private val viewModel: FragActViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val editFragmento = view.findViewById<EditText>(R.id.editMsgFragment)
        val btnFragmento = view.findViewById<Button>(R.id.btnFragment)

        /*model = activity?.let { ViewModelProvider(it).get(BetweenFragViewModel::class.java) }
        viewModel.editTextActivity.observe(viewLifecycleOwner, Observer {
            texto -> editFragmento.setText(texto)
        })
        */

        viewModel.editMensajeAct.observe(viewLifecycleOwner, Observer {
            texto -> editFragmento.setText(texto)
        })

        btnFragmento.setOnClickListener {
            val texto = "Fragmento 1 dijo: ${editFragmento.text}"
            editFragmento.setText(null)

            val frag2 = activity?.supportFragmentManager?.findFragmentById(R.id.simpleFrag)
            val editFrag2 = frag2?.activity?.findViewById<EditText>(R.id.editMsgFrag2)
            editFrag2?.setText(texto)
            //model!!.setMensajeComunicador(editFragmento.text.toString())

        }
        return view
    }

}