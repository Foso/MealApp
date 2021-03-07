package de.jensklingenberg.newmyapplication.androidApp.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.jensklingenberg.newmyapplication.shared.models.Category
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CategoryView(person: Category, modifier: Modifier = Modifier) {
    Card(elevation = 8.dp, modifier = modifier.padding(8.dp)) {
        Column {
            CoilImage(
                data = person.strCategoryThumb,
                modifier = Modifier.size(50.dp),
                contentDescription = person.strCategory
            )

            Text(person.strCategory)
        }
    }
}