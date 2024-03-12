package com.example.hrmanagementapp.alertDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hrmanagementapp.R
import com.example.hrmanagementapp.models.Notification
import com.example.hrmanagementapp.models.User
import com.example.hrmanagementapp.ui.theme.HRManagementAppTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NotificationAlertDialog(
    notification: Notification,
    onDismiss: () -> Unit
) {
    if (notification.type == 1){
        AlertDialog(
            onDismissRequest = onDismiss,

            title = { Text(text = "Уведомление \"${notification.name}\" ") },
            text = {
                   Column {
                       Text(text = notification.desc)

                        Column (
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .background(Color.DarkGray)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Отправитель",
                                color = Color(0xFFCAC4D0),
                                fontSize = 12.sp,

                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = notification.sender,
                                color = Color.White,
                                fontSize = 16.sp,

                                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
                            )
                        }
                   }
            },

            confirmButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(text = "Закрыть")
                }
            }
        )
    }
    else{
        AlertDialog(
            onDismissRequest = onDismiss,

            title = { Text(text = "Созвон \"${notification.name}\" ") },
            text = {
                Column {
                    Text(text = notification.desc)

                    Column (
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .background(Color.DarkGray)
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "Отправитель",
                                color = Color(0xFFCAC4D0),
                                fontSize = 12.sp,

                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = notification.sender,
                                color = Color.White,
                                fontSize = 16.sp,

                                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "Время и дата",
                                color = Color(0xFFCAC4D0),
                                fontSize = 12.sp,

                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "${notification.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))} в ${notification.dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                                color = Color.White,
                                fontSize = 16.sp,

                                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
                            )
                        }
                    }
                }
            },

            confirmButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(text = "Закрыть")
                }
            }
        )
    }
}

@Composable
fun UserUpdateAlertDialog(
    user: User,
    onUserEdited: (User) -> Unit,
    onDismiss: () -> Unit
){
    val emailState = remember { mutableStateOf(user?.email ?: "") }
    val discordState = remember { mutableStateOf(user?.discord ?: "") }
    val telegramState = remember { mutableStateOf(user?.telegram ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = { Text(text = "Изменение контактных данных") },
        text = {
            Column {
                Text(text = "Введите новые или измените старые контактные данные")

                Column (
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .background(Color.DarkGray)
                        .fillMaxWidth()
                ) {
                    Column {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Telegramm",
                                    fontSize = 12.sp,
                                )
                            },
                            value = telegramState.value,
                            onValueChange = { /* Обработка изменения значения */ },
                            maxLines = 1,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            singleLine = true,
                            placeholder = { Text(text = "Введите ваш телеграмм") },
                            trailingIcon = {
                                IconButton(onClick = { telegramState.value = ""}) {
                                    Icon(
                                        imageVector = Icons.Rounded.Clear,
                                        contentDescription = "Clear icon"
                                    )
                                }
                            }
                        )


                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Email",
                                    fontSize = 12.sp,
                                )
                            },
                            value = emailState.value,
                            onValueChange = { /* Обработка изменения значения */ },
                            maxLines = 1,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            singleLine = true,
                            placeholder = { Text(text = "Введите вашу электронную почту") },
                            trailingIcon = {
                                IconButton(onClick = { emailState.value = ""}) {
                                    Icon(
                                        imageVector = Icons.Rounded.Clear,
                                        contentDescription = "Clear icon"
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        )

                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Email",
                                    fontSize = 12.sp,
                                )
                            },
                            value = discordState.value,
                            onValueChange = { /* Обработка изменения значения */ },
                            maxLines = 1,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            singleLine = true,
                            placeholder = { Text(text = "Введите ваш discordId") },
                            trailingIcon = {
                                IconButton(onClick = { discordState.value = ""}) {
                                    Icon(
                                        imageVector = Icons.Rounded.Clear,
                                        contentDescription = "Clear icon"
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        )
                    }
                }
            }
        },

        confirmButton = {
            TextButton(
                onClick = {
                    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")

                    if(emailRegex.matches(emailState.value) && discordState.value.isNotEmpty() && telegramState.value.isNotEmpty()){
                        var editedUser = user

                        editedUser.email = emailState.value
                        editedUser.discord = discordState.value
                        editedUser.telegram = telegramState.value

                        onUserEdited(editedUser)
                    }
                }
            ) {
                Text(text = "Принять")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = "Закрыть")
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun NotificationAlertDialogShow() {
    HRManagementAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val dialogState = remember { mutableStateOf(false) }

            NotificationAlertDialog(
                notification = Notification(
                    type = 1,
                    name = "нейм",
                    desc = "Оч важное уведомление, кликни что бы прочесть",
                    sender = "Отправитель 1",
                    dateTime = LocalDateTime.of(2024, 3, 5, 10, 30)
                ),
                onDismiss = { dialogState.value = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserUpdateAlertDialogShow() {
    HRManagementAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val dialogState = remember { mutableStateOf(false) }

            var userEdited = remember { mutableStateOf<User>(User(
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
            )) }



            UserUpdateAlertDialog(
                user = User(
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
                ),
                onUserEdited = { updatedUser ->
                    userEdited.value = updatedUser
                    dialogState.value = false
                },
                onDismiss = { dialogState.value = false }
            )
        }
    }
}
