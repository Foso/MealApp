package de.jensklingenberg.newmyapplication.androidApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import de.jensklingenberg.newmyapplication.shared.models.Drink
import de.jensklingenberg.newmyapplication.androidApp.databinding.ItemBreedBinding

class MainAdapter() : ListAdapter<Drink, MainViewHolder>(drinkCallback) {

    var breedClickListener: (Drink) -> Unit ? = {  }

    fun setListener(breedClickListener: (Drink) -> Unit){
        this.breedClickListener=breedClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding) {
            breedClickListener(it)
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }



    companion object {
        private val drinkCallback = object : DiffUtil.ItemCallback<Drink>() {
            override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean = oldItem == newItem

            override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean = oldItem.idDrink == newItem.idDrink
        }
    }
}
