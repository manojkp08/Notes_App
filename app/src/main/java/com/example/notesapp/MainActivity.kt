//package com.example.notesapp
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.navigation.NavController
//import androidx.navigation.findNavController
//import androidx.navigation.ui.setupActionBarWithNavController
//
//
//class MainActivity : AppCompatActivity() {
//
//    lateinit var navController: NavController
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        setSupportActionBar(findViewById(R.id.toolbar)) // Set the ActionBar
//
//        navController = findNavController(R.id.fragmentContainerView)
//
//        setupActionBarWithNavController(navController)
//
//    }
//
//    override fun onNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onNavigateUp()
//    }
//}

package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.notesapptoolbar)) // Set the ActionBar

        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
