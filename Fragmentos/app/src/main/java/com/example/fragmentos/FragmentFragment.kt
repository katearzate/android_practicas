package com.example.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.fragmentos.databinding.ActivityFragmentBinding
import com.example.fragmentos.databinding.FragmentFragmentBinding

class FragmentFragment : Fragment() {

    private lateinit var binding: FragmentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentFragmentBinding.inflate(layoutInflater)

        parentFragmentManager.setFragmentResultListener("TextoBundle", this) { key, bundle ->
            val result = bundle.getString("bundleKey")
            binding.editMensajeFragment2.setText(result)

            //println(result)
        }

        companion object {

        }

        binding.btnEnviarFragment2.setOnClickListener {

        }



        return binding.root
    }
}