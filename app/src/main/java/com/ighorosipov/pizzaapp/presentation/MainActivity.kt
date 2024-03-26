package com.ighorosipov.pizzaapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ighorosipov.pizzaapp.R
import com.ighorosipov.pizzaapp.databinding.ActivityMainBinding
import com.ighorosipov.pizzaapp.presentation.menu.MenuFragment
import com.ighorosipov.pizzaapp.presentation.tabs.TabsFragment
import com.ighorosipov.pizzaapp.utils.di.appComponent
import com.ighorosipov.pizzaapp.utils.di.setTitle

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var navController: NavController? = null
    private val topLevelDestinations = setOf(getTabsDestination())

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?,
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is TabsFragment || f is NavHostFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }

    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, arguments ->
                binding.toolbar.setTitle(destination.label, binding.toolbarTextView, arguments)
                supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.apply {
            setSupportActionBar(this)
        }
        inject()
        val navController = getRootNavController()
        prepareRootNavController(navController)
        onNavControllerActivated(navController)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
        onCustomToolbarBackPress()
    }

    private fun inject() {
        appComponent().inject(this)
    }

    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController?.removeOnDestinationChangedListener(destinationListener)
        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
    }

    private fun getRootNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        return navHost.navController
    }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestinations + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    private fun prepareRootNavController(navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())
        graph.setStartDestination(
           getTabsDestination()
        )
        navController.graph = graph
    }

    private fun onCustomToolbarBackPress() {
        binding.toolbar.setNavigationOnClickListener {
            navController?.popBackStack()
        }
    }

    private fun getMainNavigationGraphId(): Int = R.navigation.nav_graph

    private fun getTabsDestination(): Int = R.id.tabsFragment

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController = null
        super.onDestroy()
        mBinding = null
    }


}