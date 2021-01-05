package com.wcg.mobilebootcamp1_jim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.navigateUp // use this for sideDrawer


class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            //setupDrawerNav()
            //setupBottomNavigationBar()
            setupAllNav()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        //setupDrawerNav()
        //setupBottomNavigationBar()
        setupAllNav()

    }

    private fun setupAllNav() {
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFrag.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navGraphIds = listOf(R.navigation.nav_graph_main)
        bottomNavigationView.setupWithNavController(navController)

        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupDrawerNav() {
        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_graph_home, R.id.nav_graph_search))

        //val navHostFrag : NavHostFragment = findViewById(R.id.nav_host_fragment)
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFrag.navController

        //val toolbar = findViewById<Toolbar>(R.id.toolbar)

        //val appBarConfiguration = AppBarConfiguration(navController.graph, findViewById(R.id.drawer_layout))
        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_graph_home, R.id.nav_graph_search))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_graph_home, R.id.nav_graph_search), drawerLayout)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
//        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
//                || super.onOptionsItemSelected(item)
    }


    private fun setupBottomNavigationBar() {
        //https://github.com/android/architecture-components-samples/blob/main/NavigationAdvancedSample/app/src/main/java/com/example/android/navigationadvancedsample/MainActivity.kt

        //val bottomNavigationView = binding.bottomNav
        //val bottomNavigationView = findNavController(R.id.bottom_nav)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        //val navGraphIds = listOf(R.navigation.nav_graph_home, R.navigation.nav_graph_shop, R.navigation.nav_graph_search, R.navigation.nav_graph_friends)
        val navGraphIds = listOf(R.navigation.nav_graph_main)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
//        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        //return currentNavController?.value?.navigateUp() ?: false  // use this w/ bottom nav only

        // this was working for side, not for bottom with side
//        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFrag.navController
//        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
//        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
//        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)

        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

}