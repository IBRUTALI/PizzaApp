package com.ighorosipov.pizzaapp.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ighorosipov.pizzaapp.R
import com.ighorosipov.pizzaapp.databinding.FragmentMenuBinding
import com.ighorosipov.pizzaapp.presentation.menu.adapter.BannerAdapter
import com.ighorosipov.pizzaapp.presentation.menu.adapter.Category
import com.ighorosipov.pizzaapp.presentation.menu.adapter.CategoryAdapter
import com.ighorosipov.pizzaapp.presentation.menu.adapter.MealAdapter
import com.ighorosipov.pizzaapp.utils.Result
import com.ighorosipov.pizzaapp.utils.di.appComponent
import com.ighorosipov.pizzaapp.utils.di.lazyViewModel


class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val bannerAdapter by lazy { BannerAdapter() }
    private val categoryAdapter by lazy { CategoryAdapter() }
    private val mealAdapter by lazy { MealAdapter() }
    private lateinit var cityAdapter: ArrayAdapter<String>

    private val cityItems = arrayOf(
        "Москва",
        "Санкт-Петербург",
        "Казань"
    )

    private val viewModel: MenuViewModel by lazyViewModel {
        requireContext().appComponent().menuViewModel().create()
    }

    private fun inject() {
        requireContext().appComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        initSortAdapter()
        initBannerAdapter()
        initCategoryAdapter()
        initMealAdapter()
        subscribeToObservers()

        categoryAdapter.setOnClickListener(object: CategoryAdapter.OnClickListener {
            override fun onCategoryClick(position: Int, category: Category) {
                viewModel.changeCategory(category)
            }
        })

        binding.cityList.onItemClickListener =
            AdapterView.OnItemClickListener { _, view, position, id ->
                viewModel.changeCity(cityItems[position])
            }
    }

    private fun initSortAdapter() {
        cityAdapter = ArrayAdapter(requireContext(), R.layout.item_city)
        cityItems.forEach {
            cityAdapter.add(it)
        }
        binding.cityList.setAdapter(cityAdapter)
    }

    private fun initBannerAdapter() {
        binding.bannerList.adapter = bannerAdapter
    }

    private fun initCategoryAdapter() {
        binding.categoryList.adapter = categoryAdapter
    }

    private fun initMealAdapter() {
        binding.mealList.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            binding.mealList.context,
            LinearLayoutManager.VERTICAL
        )
        binding.mealList.addItemDecoration(dividerItemDecoration)
        binding.mealList.adapter = mealAdapter
    }

    private fun subscribeToObservers() {
        viewModel.city.observe(viewLifecycleOwner) { city ->
            binding.cityList.setText(city, false)
        }
        viewModel.category.observe(viewLifecycleOwner) { category ->
            categoryAdapter.updateCategory(category)
        }
        viewModel.meals.observe(viewLifecycleOwner) { result ->
            when(result) {

                is Result.Loading -> {

                }

                is Result.Success -> {
                    result.data?.let { mealAdapter.setList(it) }
                }

                is Result.Error -> {

                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.cityList.setText("", false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}