package com.example.hrmanagementapp.models

import java.time.LocalDateTime

class Notification(
    var type: Int,
    var name: String,
    var desc: String,
    var sender: String,
    var dateTime: LocalDateTime
)