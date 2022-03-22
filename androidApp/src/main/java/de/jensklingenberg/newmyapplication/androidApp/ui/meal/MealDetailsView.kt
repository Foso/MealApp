package de.jensklingenberg.newmyapplication.androidApp.ui.meal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.jensklingenberg.newmyapplication.androidApp.ui.mealViewModel


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
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).build(),
                        modifier = Modifier.size(240.dp),
                        contentDescription = meal.strMeal
                    )
                }

                Text("Tags:")

                it.strTags.forEach {
                    Tag(it)
                }

                if (it.ingredients.isNotEmpty()) {
                    Text("Ingredients:")
                }

                it.ingredients.forEach { ingredient ->
                    Row() {
                        Checkbox(checked = false, onCheckedChange = { /*TODO*/ })
                        Text(ingredient.name)
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(mealViewModel.getIngredientImage(ingredient.name)).build(),
                            modifier = Modifier.size(50.dp),
                            contentDescription = meal.strMeal
                        )
                    }
                }

                Text("Instructions: " + meal.strInstructions)


            }
        }
    }
}


@Composable
fun SearchInput(
    onSearchInputSend: (String) -> Unit
) = Column {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val input = remember { mutableStateOf(TextFieldValue()) }

        fun createToDo() {
            //if (input.value.text.isBlank()) return
            onSearchInputSend(input.value.text)
            input.value = input.value.copy(text = "")
        }

        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { createToDo() }),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
        )
        OutlinedButton(
            onClick = { createToDo() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Search")
        }
    }
    Divider()
}