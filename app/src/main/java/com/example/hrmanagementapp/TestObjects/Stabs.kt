package com.example.hrmanagementapp.TestObjects
import com.example.hrmanagementapp.models.User
import java.time.LocalDate
object TestStabs {
    fun generateUserList(): List<User> {
        val userList = mutableListOf<User>()
        val statuses = listOf("Практикант", "Сотрудник", "Руководитель")

        repeat(100) {
            val status = statuses.random()
            val user = User(
                id = it + 1,
                keycloakId = it + 1,
                surname = "Фамилия$it",
                name = "Имя$it",
                patronymic = "Отчество$it",
                birthDate = LocalDate.of(2000, 1, 1), // Пример даты рождения
                status = status,
                email = "user$it@example.com",
                discord = "user$it",
                telegram = "user$it",
                loginable = true
            )
            userList.add(user)
        }

        return userList
    }
}