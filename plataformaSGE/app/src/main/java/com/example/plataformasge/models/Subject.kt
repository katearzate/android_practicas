package com.example.plataformasge.models

data class Subject (var subjectName: String,
                    var group: String,
                    var profesor: String,
                    var hourMonday: String? = "",
                    var hourTuesday: String? = "",
                    var hourWednesday: String? = "",
                    var hourThursday: String? = "",
                    var hourFriday: String? = "",
                    var score: String? = ""
)