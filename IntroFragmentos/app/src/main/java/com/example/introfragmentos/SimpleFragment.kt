package com.example.introfragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class SimpleFragment : Fragment() {

    private val viewModel: FragActViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_simple, null)

        val editFrag2 = view.findViewById<EditText>(R.id.editMsgFrag2)
        val btnFrag2 = view.findViewById<Button>(R.id.btnFrag2)

        btnFrag2.setOnClickListener {
            editFrag2.text.let {
                viewModel.setMessage("Fragmento 2 dijo: ${it}")
            }
            editFrag2.setText("")

            /*val texto = "Fragmento 2 dijo: ${editFrag2.text}"
            editFrag2.text = null
            activity?.findViewById<EditText>(R.id.editMsgActivity)?.setText(texto)
             */
        }

        return view
    }
}