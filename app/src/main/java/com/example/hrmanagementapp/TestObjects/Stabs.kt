package com.example.hrmanagementapp.TestObjects
import com.example.hrmanagementapp.models.Notification
import com.example.hrmanagementapp.models.User
import java.time.LocalDate
import java.time.LocalDateTime

object TestStabs {
    fun generateUserList(): List<User> {
        val userList = mutableListOf<User>()
        val statuses = listOf("Практикант", "Сотрудник", "Руководитель")

        repeat(100) { it ->
            val status = statuses[it % statuses.size] // Циклический выбор статуса из списка
            val user = User(
                id = it + 1,
                keycloakId = it + 1,
                surname = "Фамилия${it % 10}", // Использование остатка от деления для циклического выбора фамилии
                name = "Имя${it % 10}", // Использование остатка от деления для циклического выбора имени
                patronymic = "Отчество${it % 10}", // Использование остатка от деления для циклического выбора отчества
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

    fun generateUser(userId: Int ): User {
        var userList = generateUserList()

        if(userId < 0 || userId > userList.count()-1){
            return User(
                id = 1,
                keycloakId = 1,
                surname = "Дубовик",
                name = "Денис",
                patronymic = "Алексеевич",
                birthDate = LocalDate.of(2005, 1, 13), // Используем LocalDate здесь
                status = "Практикант",
                discord = "Test",
                email = "example@gmail.com",
                telegram = "test",
                loginable = true
            )
        }
        else{
            return userList[userId]
        }
    }

    fun generateNotificationList(): List<Notification> {
        return listOf(
            Notification(
                type = 1,
                name = "нейм",
                desc = "Оч важное уведомление, кликни что бы прочесть",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 5, 10, 30)
            ),
            Notification(
                type = 2,
                name = "нейм",
                desc = "Оч важное описание созвона",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 6, 15, 45)
            ),
            Notification(
                type = 1,
                name = "нейм",
                desc = "Оч важное уведомление, кликни что бы прочесть",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 5, 10, 30)
            ),
            Notification(
                type = 2,
                name = "нейм",
                desc = "Оч важное описание созвона",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 6, 15, 45)
            ),
            Notification(
                type = 1,
                name = "нейм",
                desc = "Оч важное уведомление, кликни что бы прочесть",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 5, 10, 30)
            ),
            Notification(
                type = 2,
                name = "нейм",
                desc = "Оч важное описание созвона",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 6, 15, 45)
            ),
            Notification(
                type = 1,
                name = "нейм",
                desc = "Оч важное уведомление, кликни что бы прочесть",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 5, 10, 30)
            ),
            Notification(
                type = 2,
                name = "нейм",
                desc = "Оч важное описание созвона",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 6, 15, 45)
            ),
            Notification(
                type = 1,
                name = "нейм",
                desc = "Оч важное уведомление, кликни что бы прочесть",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 5, 10, 30)
            ),
            Notification(
                type = 2,
                name = "нейм",
                desc = "Оч важное описание созвона",
                sender = "Отправитель 1",
                dateTime = LocalDateTime.of(2024, 3, 6, 15, 45)
            )
        )
    }

    fun generateNotification(notificationId: Int): Notification{
        var notifications = generateNotificationList()

        if(notificationId < 0 || notificationId > notifications.count()-1){
            return Notification(
                type = 1,
                name = "Нет данных",
                desc = "Нет данных",
                sender = "Нет данных",
                dateTime = LocalDateTime.of(1970, 1, 1, 0, 0)
            )
        }
        else{
            return notifications[notificationId]
        }
    }
}