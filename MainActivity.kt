package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.FirstScreen.route){
        composable(route = Screen.FirstScreen.route) { FirstScreen(navController) }
        composable(route = Screen.SecondScreen.route) {SecondScreen(navController)}
    }

}

    @Composable
    fun FirstScreen(navController: NavController) {
        var data by remember{ mutableStateOf("")}
        Scaffold(
            topBar = { TopAppBar( title = { Text("First Screen")} ) },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    OutlinedTextField(
                        value = data,
                        label = { Text("Name") },
                        onValueChange = { value ->
                            data = value
                        }
                    )
                    Spacer(Modifier.height(20.dp))
                   Button(onClick = {
                       navController.currentBackStackEntry?.arguments = Bundle()
                           .apply { putString("name", data) }
                       navController.navigate(Screen.SecondScreen.route)
                   }) {
                        Text("Done", fontSize = 20.sp)
                    }
                }
            }
        )
    }

    @Composable
    fun SecondScreen(navController: NavController) {
        val data = navController.previousBackStackEntry?.arguments?.get("name")
        Scaffold(
            topBar = { TopAppBar(
                title = { Text("Second Screen") },
                navigationIcon = { IconButton(
                    onClick = { navController.popBackStack() },
                    content = { Icon(Icons.Default.ArrowBack, "") }
                )}
            ) },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Hi, $data",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }

