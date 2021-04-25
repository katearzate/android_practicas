package com.example.introfragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class SimpleFragment : Fragment() {

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
            val texto = "Fragmento 2 dijo: ${editFrag2.text}"
            editFrag2.text = null

            activity?.findViewById<EditText>(R.id.editMsgActivity)?.setText(texto)
        }

        return view
    }
}