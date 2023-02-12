package fr.systemathicdev.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import fr.systemathicdev.newsapp.R
import fr.systemathicdev.newsapp.databinding.ActivityMainBinding
import org.koin.standalone.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigateToArticles(navHostFragment.navController)
    }

    private fun navigateToArticles(navController: NavController){
        navController.run {
            setGraph(R.navigation.nav_graph_main)
        }
    }
}