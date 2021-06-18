package com.example.plataformasge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.adapters.ElectionAdapter
import com.example.plataformasge.databinding.ActivitySubjectsElectionBinding
import com.example.plataformasge.models.DBManager

class SubjectsElectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubjectsElectionBinding

    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsElectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _dbManager = DBManager(this, "escolar", null, 1)

        binding.recyclerElection.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        //TODO: mandar llamar una funcion desde el dbmanager para llenar la reticula
        //binding.recyclerElection.adapter = ElectionAdapter(this, )

    }
}