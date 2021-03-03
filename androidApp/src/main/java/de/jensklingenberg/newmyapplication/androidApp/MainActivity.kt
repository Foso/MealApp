package de.jensklingenberg.newmyapplication.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import de.jensklingenberg.newmyapplication.androidApp.CocktailViewModel
import de.jensklingenberg.newmyapplication.androidApp.adapter.MainAdapter
import de.jensklingenberg.newmyapplication.androidApp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val peopleInSpaceViewModel: CocktailViewModel = CocktailViewModel()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        lifecycleScope.launch {
            peopleInSpaceViewModel.flowgetDrinks().collect {
                adapter.submitList(it.drinks)
            }
        }

        adapter.setListener {
            it
        }
        binding.breedList.adapter = adapter
        binding.breedList.layoutManager = LinearLayoutManager(this)

    }
}
