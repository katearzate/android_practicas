package com.example.plataformaescolarv2.getters

import java.io.Serializable
import kotlin.collections.ArrayList

class Horario(var dia: String, var materias: ArrayList<MateriaHorarios>) : Serializable {
}