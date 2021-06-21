package com.example.plataformasge.models

data class Subject (var subjectName: String?,
                    var group: String? = "",
                    var profesor: String? = "",
                    var credits: Int? = 0,
                    var score: String? = "",
                    var code: String?,
                    var hourMonday: String? = "",
                    var hourTuesday: String? = "",
                    var hourWednesday: String? = "",
                    var hourThursday: String? = "",
                    var hourFriday: String? = ""
)