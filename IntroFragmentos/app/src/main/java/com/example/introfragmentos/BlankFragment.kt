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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {
    
    //private val viewModel: FragmentsViewModel by activityViewModels()
    //private var model: BetweenFragViewModel?=null

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

            println("Param1: $param1")
            println("Param2: $param2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val editFragmento = view.findViewById<EditText>(R.id.editMsgFragment)
        val btnFragmento = view.findViewById<Button>(R.id.btnFragment)

        param1?.let {
            val texto = "El activity dice: $it"
            editFragmento.setText(texto)
        }

        param2?.let {
            println("Parametro 2: $it")
        }

        //model = activity?.let { ViewModelProvider(it).get(BetweenFragViewModel::class.java) }
      /*  viewModel.editTextActivity.observe(viewLifecycleOwner, Observer {
            texto -> editFragmento.setText(texto)
        })
*/
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}