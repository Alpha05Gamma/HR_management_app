package com.example.hrmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hrmanagementapp.ui.theme.HRManagementAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.hrmanagementapp.TestObjects.TestStabs
import com.example.hrmanagementapp.TestObjects.TestStabs.generateNotificationList
import com.example.hrmanagementapp.TestObjects.TestStabs.generateUser
import com.example.hrmanagementapp.alertDialogs.NotificationAlertDialog
import com.example.hrmanagementapp.alertDialogs.UserUpdateAlertDialog
import com.example.hrmanagementapp.models.Notification
import com.example.hrmanagementapp.models.User
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {

    private lateinit var receivedUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receivedUser = intent.getParcelableExtra("user")!!

        setContent {
            HRManagementAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityUserScreen(receivedUser)
                }
            }
        }
    }
}


@Composable
fun MainActivityUserScreen(user: User) {

    val lContext = LocalContext.current

    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selectedMenuItem by remember { mutableStateOf("profile") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Навигация", modifier = Modifier.padding(16.dp))
                Divider(modifier = Modifier.padding(bottom = 16.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Профиль") },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_circle_24),
                            contentDescription = "Круг" // Описание для доступности
                        )
                    },

                    selected = selectedMenuItem == "profile",
                    onClick = {
                        selectedMenuItem = "profile"
                        navController.navigate("profile")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Список персонала") },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_circle_24),
                            contentDescription = "Круг" // Описание для доступности
                        )
                    },

                    selected = selectedMenuItem == "personalList",
                    onClick = {
                        selectedMenuItem = "personalList"
                        navController.navigate("personalList")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Уведомления") },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_circle_24),
                            contentDescription = "Круг" // Описание для доступности
                        )
                    },

                    selected = selectedMenuItem == "notifications",
                    onClick = {
                        selectedMenuItem = "notifications"
                        navController.navigate("notifications")
                        scope.launch { drawerState.close() }
                    }
                )
                Divider(modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Выйти") },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_circle_24),
                            contentDescription = "Круг" // Описание для доступности
                        )
                    },

                    selected = selectedMenuItem == "logOut",
                    onClick = {
                        selectedMenuItem = "logOut"
                        navController.navigate("logOut")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
    ) {
        Scaffold(
            floatingActionButton = {
                SmallFloatingActionButton(
                    onClick = { scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    } },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Info, "Навигация")
                }
            }
        ) { contentPadding ->
            Box(
                Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            ) {
                NavHost(navController, startDestination = "profile") {
                    composable("profile") { ProfileScreen(navController, user, isEditable = true) }
                    composable("personalList") { PersonalListScreen(navController) }
                    composable("notifications") { NotificationsScreen(navController) }
                    composable("logOut") {
                        val intent = Intent(lContext, AuthActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        lContext.startActivity(intent)
                    }

                    composable("profilePreview/{userId}", arguments = listOf(navArgument("userId") { type = NavType.IntType })) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getInt("userId")
                        if (userId != null) {
                            ProfileScreen(navController, generateUser(userId), isEditable = false)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController, user: User, isEditable: Boolean) {
    val showDialog = remember { mutableStateOf(false) }
    val userUpdated = remember { mutableStateOf(user) }

    Scaffold(
        floatingActionButton = {
            if(isEditable){
                ExtendedFloatingActionButton(
                    onClick = {
                        showDialog.value = true
                    },
                    icon = { Icon(Icons.Filled.Create, "Изменение") },
                    text = { Text(text = "Изменить") },
                    modifier = Modifier.padding(end = 210.dp)
                )
            }
        }
    ) { contentPadding ->
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Box(
                modifier = Modifier.padding(top = 50.dp)
            ) {
                Column {
                    Text(text = "${userUpdated.value.name} ${userUpdated.value.patronymic} ${userUpdated.value.surname}", fontSize = 36.sp, lineHeight = 35.sp, modifier = Modifier.padding(start = 30.dp, bottom = 15.dp))
                    Row (
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        val str2 = "M4-08-12"

                        Text(text = "Статус: ${userUpdated.value.status}", modifier = Modifier.padding(start = 20.dp))
                        Text(text = "Группа: $str2", modifier = Modifier.padding(start = 20.dp))
                    }
                    Row(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(text = "Мои контакты", fontSize = 24.sp)
                    }

                    Row(
                        modifier =  Modifier.padding(start = 10.dp)
                    ){
                        Column {
                            Text(text = "Telegramm: ${userUpdated.value.telegram}")
                            Text(text = "Email: ${userUpdated.value.email}")
                            Text(text = "Discord: ${userUpdated.value.discord}")
                        }
                    }
                }
            }
        }
    }
    if (showDialog.value){
        UserUpdateAlertDialog(
            user = user,
            onUserEdited = {editedUser ->
                userUpdated.value = editedUser
                showDialog.value = false
            },
            onDismiss = { showDialog.value = false }
        )

    }
}

@Composable
fun PersonalListScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        val userList = TestStabs.generateUserList()

        
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("Список персонала", fontSize = 36.sp)
            Text(text = "Фильтры", fontSize = 24.sp, modifier = Modifier.padding(top = 10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                var expanded by remember { mutableStateOf(false) }
                var selectedText by remember { mutableStateOf("Все") }

                val states = listOf("Все", "Практикант", "Сотрудник", "Руководитель")

                Text(text = "Статус: ", Modifier.padding(end = 9.dp))
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 200.dp)
                        .clickable { expanded = true }
                        .background(Color.DarkGray)
                        .border(color = Color.White, width = 2.dp)
                ) {
                    Text(text = selectedText, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(5.dp))
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    states.forEach {state->
                        DropdownMenuItem(text = { Text(text = state)}, onClick = {
                            selectedText = state
                            expanded = false
                        })
                    }
                }
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                var expanded by remember { mutableStateOf(false) }
                var selectedText by remember { mutableStateOf("Все") }

                val states = listOf("М4–08-12", "М4–08-13", "М4–08-14", "Все")

                Text(text = "Группы: ", Modifier.padding(end = 5.dp))
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 200.dp)
                        .clickable { expanded = true }
                        .background(Color.DarkGray)
                        .border(color = Color.White, width = 2.dp)
                ) {
                    Text(text = selectedText, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(5.dp))
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    states.forEach {state->
                        DropdownMenuItem(text = { Text(text = state)}, onClick = {
                            selectedText = state
                            expanded = false
                        })
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                var query: String by rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier.fillMaxWidth(),

                    value = query,
                    onValueChange = { onQueryChanged ->
                        query = onQueryChanged
                        if (onQueryChanged.isNotEmpty()) {
                        }
                    },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.bodySmall,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(R.string.hint_search_by_second_name)) },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search icon"
                        )
                    },

                    trailingIcon = {
                        IconButton(onClick = { query = ""}) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Clear icon"
                            )
                        }
                    }
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                var query: String by rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier.fillMaxWidth(),

                    value = query,
                    onValueChange = { onQueryChanged ->
                        query = onQueryChanged
                        if (onQueryChanged.isNotEmpty()) {
                        }
                    },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.bodySmall,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(R.string.hint_search_by_user_id)) },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search icon"
                        )
                    },

                    trailingIcon = {
                        IconButton(onClick = { query = ""}) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Clear icon"
                            )
                        }
                    }
                )
            }

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
                ){

                LazyColumn {
                    items(userList){ user ->

                        Column(
                            Modifier
                                .padding(5.dp)
                                .clickable {
                                    navController.navigate(
                                        route = "profilePreview/${
                                            userList.indexOf(
                                                user
                                            )
                                        }"
                                    )
                                }
                        ) {
                            Text(text = user.name, fontSize = 16.sp)
                            Text(text = user.status, fontSize = 14.sp, color = Color.LightGray)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        val notifications = generateNotificationList()

        val dialogState = remember { mutableStateOf(false) }

        var targetNotification = remember { mutableStateOf<Notification?>(notifications[0]) }

        Column(
            verticalArrangement = Arrangement.Top,

            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("Уведомления", style = MaterialTheme.typography.headlineLarge)


            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
            ){

                LazyColumn {
                    items(notifications){ notificationListItem ->

                        Column(
                            Modifier
                                .padding(5.dp)
                                .clickable {
                                    targetNotification.value = notificationListItem
                                    dialogState.value = true
                                }
                        ) {
                            if (notificationListItem.type == 1){
                                Text(text = "Уведомление \"${notificationListItem.name}\"", fontSize = 16.sp, maxLines = 1)
                                Text(text = notificationListItem.desc, fontSize = 14.sp, color = Color.LightGray, maxLines = 1)
                            }
                            else{
                                Text(text = "Созвон \"${notificationListItem.name}\" в ${notificationListItem.dateTime.format(
                                    DateTimeFormatter.ofPattern("HH:mm"))}", fontSize = 16.sp)
                                Text(text = notificationListItem.desc, fontSize = 14.sp, color = Color.LightGray)
                            }
                        }

                    }
                }

                if (dialogState.value) {
                    NotificationAlertDialog(
                        notification = targetNotification.value!!,
                            onDismiss = { dialogState.value = false }
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HRManagementAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainActivityUserScreen(User(
                    id = 1,
                    keycloakId = 1,
                    surname = "Дубовик",
                    name = "Денис",
                    patronymic = "Алексеевич",
                    birthDate = LocalDate.of(2005, 1, 13),
                    status = "Практикант",
                    discord = "Test",
                    email = "example@gmail.com",
                    telegram = "test",
                    loginable = true
                )
            )
        }
    }
}