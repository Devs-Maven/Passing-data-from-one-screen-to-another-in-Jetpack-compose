package com.example.myapplication



    sealed class Screen(val route: String){
        object FirstScreen : Screen("First Screen")
        object SecondScreen : Screen("Second Screen")

    }
