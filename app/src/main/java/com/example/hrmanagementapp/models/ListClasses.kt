package com.example.hrmanagementapp.models

import java.time.LocalDateTime

class UserListItem(
    var name: String,
    var status: String
)

class NotificationListItem(
    var type: Int,
    var name: String,
    var desc: String,
    var sender: String,
    var dateTime: LocalDateTime
)