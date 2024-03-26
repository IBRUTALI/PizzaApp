package com.ighorosipov.pizzaapp.di

import android.app.Application
import com.ighorosipov.pizzaapp.presentation.MainActivity
import com.ighorosipov.pizzaapp.presentation.basket.BasketFragment
import com.ighorosipov.pizzaapp.presentation.menu.MenuFragment
import com.ighorosipov.pizzaapp.presentation.menu.MenuViewModel
import com.ighorosipov.pizzaapp.presentation.profile.ProfileFragment
import com.ighorosipov.pizzaapp.presentation.tabs.TabsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(mainActivity: MainActivity)
    fun inject(tabsFragment: TabsFragment)
    fun inject(menuFragment: MenuFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(basketFragment: BasketFragment)

    fun menuViewModel(): MenuViewModel.Factory
}