package com.example.plataformasge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformasge.databinding.ActivitySubjectsElectionBinding

class SubjectsElectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubjectsElectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsElectionBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}