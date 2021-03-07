package de.jensklingenberg.newmyapplication.androidApp.ui.meal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.jensklingenberg.newmyapplication.shared.models.Meal
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MealView(mealImageUrl: String, person: Meal, mealSelected: (meal: Meal) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { mealSelected(person) })
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        if (mealImageUrl.isNotEmpty()) {
            CoilImage(
                data = mealImageUrl,
                modifier = Modifier.size(60.dp),
                contentDescription = person.strMeal
            )
        } else {
            Spacer(modifier = Modifier.size(60.dp))
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(text = person.strMeal, style = TextStyle(fontSize = 20.sp))
        }
    }
}