package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.clases.AdapterMaterias
import com.example.plataformaescolar.clases.Materia
import com.example.plataformaescolar.databinding.ActivityHorarioBinding

class HorarioActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityHorarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_horario)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val lista1 : MutableList<Materia> = mutableListOf()
        lista1.add(Materia("08:00", "Administracion y Seguridad de Redes"))
        lista1.add(Materia("10:00","Redes de Computadoras"))
        lista1.add(Materia("13:00","Telecomunicaciones"))
        lista1.add(Materia("14:00","Sistemas Operativos"))
        lista1.add(Materia("15:00", "Introduccion a la Seguridad Informatica"))
        lista1.add(Materia("17:00", "Desarrollo de Dispositivos Moviles"))

        val lista2 : MutableList<Materia> = mutableListOf()
        lista2.add(Materia("08:00", "Administracion y Seguridad de Redes"))
        lista2.add(Materia("09:00","Redes de Computadoras"))
        lista2.add(Materia("13:00","Telecomunicaciones"))
        lista2.add(Materia("14:00","Sistemas Operativos"))
        lista2.add(Materia("17:00", "Desarrollo de Dispositivos Moviles"))

        val lista3 : MutableList<Materia> = mutableListOf()
        lista3.add(Materia("10:00","Redes de computadoras"))
        lista3.add(Materia("13:00","Telecomunicaciones"))
        lista3.add(Materia("14:00","Sistemas operativos"))
        lista3.add(Materia("15:00", "Introduccion a la Seguridad Informatica"))

        val lista4 : MutableList<Materia> = mutableListOf()
        lista4.add(Materia("08:00", "Administracion y Seguridad de Redes"))
        lista4.add(Materia("09:00","Redes de Computadoras"))
        lista4.add(Materia("13:00","Telecomunicaciones"))
        lista4.add(Materia("14:00","Sistemas Operativos"))
        lista4.add(Materia("17:00", "Desarrollo de Dispositivos Moviles"))


        val lista5 : MutableList<Materia> = mutableListOf()
        lista5.add(Materia("09:00","Redes de Computadoras"))
        lista5.add(Materia("13:00","Telecomunicaciones"))
        lista5.add(Materia("14:00","Sistemas Operativos"))


        binding.listViewLunes.adapter = AdapterMaterias(this, R.layout.lista_materias, lista1)
        binding.listViewMartes.adapter = AdapterMaterias(this, R.layout.lista_materias, lista2)
        binding.listViewMiercoles.adapter = AdapterMaterias(this, R.layout.lista_materias, lista3)
        binding.listViewJueves.adapter = AdapterMaterias(this, R.layout.lista_materias, lista4)
        binding.listViewViernes.adapter = AdapterMaterias(this, R.layout.lista_materias, lista5)

    }
}