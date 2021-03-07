package de.jensklingenberg.newmyapplication.androidApp.ui.meal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.jensklingenberg.newmyapplication.androidApp.ui.mealViewModel
import de.jensklingenberg.newmyapplication.shared.models.getIngredients
import de.jensklingenberg.newmyapplication.shared.models.tags
import dev.chrisbanes.accompanist.coil.CoilImage

@Preview
@Composable
fun MealDetailsViewPreview() {
    MealDetailsView(mealName = "test", popBack = { /*TODO*/ })
}

@Composable
fun Tag(name:String) {
    Card(elevation = 8.dp) {
        Text(name)
    }
}


@Preview( showSystemUi = true)
@Composable
fun TagPreview() {
    Tag("test")
}

@Composable
fun MealDetailsView(mealName: String, popBack: () -> Unit) {
    val state = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(mealName) },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(state),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val meal = mealViewModel.getMeal(mealName)
            meal?.let {
                Text(meal.strMeal, style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.size(12.dp))

                val imageUrl = mealViewModel.getMealImage(meal.strMeal)
                if (imageUrl.isNotEmpty()) {
                    CoilImage(
                        data = imageUrl,
                        modifier = Modifier.size(240.dp),
                        contentDescription = meal.strMeal
                    )
                }

                Text("Tags:")

                it.tags().forEach {
                    Tag(it)
                }

                if (it.getIngredients().isNotEmpty()) {
                    Text("Ingredients:")
                }

                it.getIngredients().forEach { ingredient ->
                    Row {
                        Checkbox(checked = false, onCheckedChange = { /*TODO*/ })
                        Text(ingredient.name)
                        CoilImage(
                            data = mealViewModel.getIngredientImage(ingredient.name),
                            modifier = Modifier.size(50.dp),
                            contentDescription = meal.strMeal
                        )
                    }
                }

               // Text("Category: " + meal.strCategory, style = MaterialTheme.typography.h4)
                Text("Instructions: " + meal.strInstructions)


            }
        }
    }
}