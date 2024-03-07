package com.example.hrmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hrmanagementapp.models.User
import com.example.hrmanagementapp.ui.theme.HRManagementAppTheme
import java.time.LocalDate
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import kotlinx.coroutines.launch

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HRManagementAppTheme {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AuthScreen()
                }
            }
        }
    }
}

@Composable
fun AuthScreen() {

    val lContext = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold (
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,

                modifier = Modifier.weight(1f).padding(bottom = 50.dp)

            ) {
                Text(
                    style = MaterialTheme.typography.headlineLarge,
                    text = "Добро пожаловать",
                    fontSize = 24.sp,
                )
            }
            Box (
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.weight(2f)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(
                                vertical =  30.dp,
                                horizontal = 55.dp
                            )
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "Это внутреннее приложение HR портала Marlo. Для продолжения пожалуйста авторизуйтесь",
                            fontSize = 14.sp
                        )
                    }
                    Row {
                        OutlinedButton (
                            onClick = {
                                val testUser = User(
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

                                if(testUser.loginable){
                                    val intent = Intent(lContext, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

                                    intent.putExtra("user", testUser)

                                    lContext.startActivity(intent)
                                }
                                else{
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Авторизация невозможна")
                                    }
                                }
                            }
                        ) {
                            Text(text = "Авторизация через MarloId")
                        }
                    }
                }
            }
            Row {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthActivityPreview() {
    HRManagementAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AuthScreen()
        }
    }
}