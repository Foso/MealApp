package de.jensklingenberg.newmyapplication.androidApp.ui.meal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun MealView(mealImageUrl: String, meal: de.jensklingenberg.network.model.Meal, mealSelected: (meal: de.jensklingenberg.network.model.Meal) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { mealSelected(meal) })
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        if (mealImageUrl.isNotEmpty()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(mealImageUrl).build(),
                modifier = Modifier.size(60.dp),
                contentDescription = meal.strMeal
            )
        } else {
            Spacer(modifier = Modifier.size(60.dp))
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(text = meal.strMeal, style = TextStyle(fontSize = 20.sp))
        }
    }
}