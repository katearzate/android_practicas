package com.example.plataformasge.adapters

import android.content.Context
import android.os.Build
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.R
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.Score
import com.example.plataformasge.models.Subject
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

abstract class ElectionItemsAdapter (var context: Context, var subjects: List<Score>)
    : RecyclerView.Adapter<ElectionItemsAdapter.ViewHolder>(){

    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!
    private val hashMap: HashMap<String, Subject> = hashMapOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var score: TextView = itemView.findViewById(R.id.listSubjectScore)
        val subjectName: TextView = itemView.findViewById(R.id.listSubjectName)
        val btnSelectSubject: MaterialButton = itemView.findViewById(R.id.listSubjectBtnSelectSubject)
        val reticulaBackground: LinearLayout =  itemView.findViewById(R.id.listSubjectBackground)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.list_reticula_subject,
            parent,
            false
        )

        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var subject: Score = subjects.get(position)
        holder.score.setText(subject.score)
        holder.subjectName.setText(subject.subjectName)

        holder.btnSelectSubject.setOnClickListener {
            showPopup(subject, holder)
        }
        subjectStatus(holder)
    }

    override fun getItemCount(): Int = subjects.size

    fun showPopup(subject: Score, holder: ElectionItemsAdapter.ViewHolder){
        val inflater:LayoutInflater = LayoutInflater.from(context) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_available_subjects,null)

        val popupWindow = PopupWindow(
            view,
            LinearLayout.LayoutParams.WRAP_CONTENT,     //window width
            LinearLayout.LayoutParams.WRAP_CONTENT      //window height
        )

        popupWindow.width = 1000
        popupWindow.height = 1200

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            popupWindow.elevation = 20.0F
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            val slideOut = Slide()
            slideOut.slideEdge = Gravity.BOTTOM
            popupWindow.exitTransition = slideOut
        }

        val subjectName = view.findViewById<TextView>(R.id.popupSubjectName)
        subjectName.setText("Materia: ${subject.subjectName}")

        //*********************** SHOW GROUPS AVAILABLE *********************
        _dbManager = DBManager(context, "escolar", null, 1)

        val listGroups = view.findViewById<ListView>(R.id.popupListGroups)

        listGroups.adapter = object : GroupsAdapter(
            context,
            R.layout.list_available_subjects,
            dbManager.showGroups(subject.subjectName.toString())
        ){
            override fun groupSelected(group: Subject) {
                //hashMap.put(group.code!!, group)
                popupWindow.dismiss()

                groupSelectedElectionItem(group)
                if (subject.score == "no cursada"){
                    subject.score = "cursando"
                    holder.score.text = "cursando"
                    subjectStatus(holder)
                    holder.score.visibility = View.VISIBLE
                    holder.btnSelectSubject.visibility = View.GONE
                }else if (subject.score!!.toInt() < 70){
                    subject.score = "reprobada"
                    holder.score.setText("reprobada")
                    subjectStatus(holder)
                    holder.score.visibility = View.VISIBLE
                    holder.btnSelectSubject.visibility = View.GONE
                }
            }
        }

        val btnCerrarPopup = view.findViewById<ExtendedFloatingActionButton>(R.id.popupbtnClose)
        btnCerrarPopup.setOnClickListener{
            popupWindow.dismiss()
        }

        TransitionManager.beginDelayedTransition(view as ViewGroup?)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    fun subjectStatus(holder: ElectionItemsAdapter.ViewHolder){
        when(holder.score.text.toString()){
            "cursando" -> {
                holder.reticulaBackground.setBackgroundColor(context.resources.getColor(R.color.matCursando))
            }
            "no cursada" -> {
                holder.btnSelectSubject.visibility = View.VISIBLE
                holder.score.visibility = View.GONE
                holder.reticulaBackground.setBackgroundColor(context.resources.getColor(R.color.matDisponible))
            }
            "reprobada" -> {
                holder.reticulaBackground.setBackgroundColor(context.resources.getColor(R.color.matReprobada))
            }
            "ACA" -> {
                holder.reticulaBackground.setBackgroundColor(context.resources.getColor(R.color.extracurricular))
            }
            else -> {
                if(holder.score.text.toString().toInt() < 70){
                    holder.btnSelectSubject.visibility = View.VISIBLE
                    holder.score.visibility = View.GONE
                    holder.reticulaBackground.setBackgroundColor(context.resources.getColor(R.color.matSinAcreditar))
                }
            }
        }
    }

    abstract fun groupSelectedElectionItem(group: Subject)
}