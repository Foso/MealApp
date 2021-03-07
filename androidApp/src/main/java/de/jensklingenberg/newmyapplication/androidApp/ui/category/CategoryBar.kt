package de.jensklingenberg.newmyapplication.androidApp.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.jensklingenberg.newmyapplication.shared.models.Category

@Composable
fun CategoryBar(categories: List<Category>, onClick: (Category) -> Unit = {}) {
    LazyRow {
        items(categories) { person ->
            CategoryView(person, modifier = Modifier.clickable { onClick(person) })
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun CategoryViewPreview() {
    CategoryView(person = Category("1", "Hallo", "", "Food"))
}