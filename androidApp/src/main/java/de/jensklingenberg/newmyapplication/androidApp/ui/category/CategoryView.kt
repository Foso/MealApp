package de.jensklingenberg.newmyapplication.androidApp.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.jensklingenberg.mealdbapi.Category


@Composable
fun CategoryView(category: Category, modifier: Modifier = Modifier) {
    Card(elevation = 8.dp, modifier = modifier.padding(8.dp)) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(category.strCategoryThumb).build(),
                modifier = Modifier.size(50.dp),
                contentDescription = category.strCategory
            )

            Text(category.strCategory)
        }
    }
}