package de.jensklingenberg.newmyapplication.androidApp.adapter

import androidx.recyclerview.widget.RecyclerView
import de.jensklingenberg.newmyapplication.shared.models.Drink
import de.jensklingenberg.newmyapplication.androidApp.databinding.ItemBreedBinding


class MainViewHolder(binding: ItemBreedBinding, breedClickListener: (Drink) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    var breedi : Drink?= null
    private val nameTextView = binding.breedNameTextView
    private val favoriteButton = binding.favoriteButton

    init {
        binding.breedNameTextView.setOnClickListener {
            // breedClickListener(adapterPosition)
            breedClickListener(breedi!!)
        }
        favoriteButton.setOnClickListener { breedClickListener(breedi!!) }
    }

    fun bindTo(breed: Drink) {
        breedi=breed
        nameTextView.text = breed.strDrink

    }
}
