package com.example.projectubereats.ui.notifications

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectubereats.EditProfileActivity
import com.example.projectubereats.R
import com.example.projectubereats.models.User
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.w3c.dom.Text

class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by activityViewModels()
    private lateinit var user: User

    private lateinit var mail: TextView
    private lateinit var name: TextView
    private lateinit var tel: TextView
    private lateinit var btnModif: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        mail = root.findViewById(R.id.notifMail)
        name = root.findViewById(R.id.notifName)
        tel = root.findViewById(R.id.notifTel)
        btnModif = root.findViewById(R.id.notifBtnChange)

        notificationsViewModel.userLog.observe(viewLifecycleOwner, Observer{ u ->
            u?.let {
                user = it
                mail.setText("Correo: ${it.usr}")
                name.setText("Nombre: ${it.name}")
                tel.setText("Telefono: ${it.celphone}")
                println("USER: $it")

                btnModif.setOnClickListener {
                    val intent = Intent(requireContext(), EditProfileActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }

            }
        })

        return root
    }

}