package com.example.projectubereats.ui.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectubereats.R
import com.example.projectubereats.models.User
import org.w3c.dom.Text

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var user: User

    private lateinit var mail: TextView
    private lateinit var name: TextView
    private lateinit var tel: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        mail = root.findViewById(R.id.notifMail)
        name = root.findViewById(R.id.notifName)
        tel = root.findViewById(R.id.notifTel)

        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        notificationsViewModel.getUser()?.observe(viewLifecycleOwner, { u ->
            u?.let {
                user = it
                mail.setText("Correo: ${it.usr}")
                name.setText("Nombre: ${it.name}")
                tel.setText("Telefono: ${it.celphone}")
                println("USER: $it")
            }
        })*/

        return root
    }

}