package com.example.fragmentos

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fragmentos.databinding.ActivityFragmentBinding

class ActivityFragment : Fragment() {

    private lateinit var binding: ActivityFragmentBinding
    private val viewModel: ActivityFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //super.onCreateView(inflater, container, savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        //val view = inflater.inflate(R.layout.activity_fragment, null)


        viewModel.editMensajeIngresado.observe(viewLifecycleOwner, Observer { texto ->
            binding.editMensajeFragment1.setText(texto)
            println(texto)
        })


        binding.btnEnviarFragment1.setOnClickListener {
            val result = "Fragmento 1 dijo que: ${binding.editMensajeFragment1.text}"
            childFragmentManager.setFragmentResult("TextoBundle", bundleOf("bundleKey" to result))

            //println(result)
            binding.editMensajeFragment1.setText("")
        }


        /*
        ************ Otro metodo para comunicarse con el sig. fragmento ************
        btnFragmento.setOnClickListener {
            val texto = "Fragmento 1 dijo: ${binding.editMensajeFragment1.text}"
            binding.editMensajeFragment1.setText(null)

            val frag2 = activity?.supportFragmentManager?.findFragmentById(R.id.FragmentFragment)
            val editFrag2 = frag2?.activity?.findViewById<EditText>(R.id.binding.editMensajeFragment1)
            editFrag2?.setText(texto)
        }
        * **************************************************************************
         */


        return binding.root
    }
}