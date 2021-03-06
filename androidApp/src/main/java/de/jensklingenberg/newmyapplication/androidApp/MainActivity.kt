package de.jensklingenberg.newmyapplication.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import de.jensklingenberg.newmyapplication.shared.PeopleInSpaceRepository
import de.jensklingenberg.newmyapplication.shared.models.Meal
import dev.chrisbanes.accompanist.coil.CoilImage

//val peopleInSpaceViewModel = CocktailViewModel(PeopleInSpaceRepository())
val peopleInSpaceViewModel = MealViewModel(PeopleInSpaceRepository())

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainLayout()
        }
    }
}


sealed class Screen(val title: String) {
    object PersonListScreen : Screen("PersonList")
    object PersonDetailsDetails : Screen("PersonDetails")
}


@Composable
fun MainLayout() {
    val navController = rememberNavController()

    PeopleInSpaceTheme {
        NavHost(navController, startDestination = Screen.PersonListScreen.title) {
            composable(Screen.PersonListScreen.title) {
                PersonList(
                    personSelected = {
                        navController.navigate(Screen.PersonDetailsDetails.title + "/${it.strMeal}")
                    }
                )
            }
            composable(Screen.PersonDetailsDetails.title + "/{person}") { backStackEntry ->
                PersonDetailsView(
                    backStackEntry.arguments?.get("person") as String,
                    popBack = { navController.popBackStack() })
            }
        }
    }
}


@Composable
fun PersonList(personSelected : (person : Meal) -> Unit) {
    val peopleState = peopleInSpaceViewModel.peopleInSpace.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meal Catalog") })
        }) {
        Column {
            Divider(thickness = 2.dp)
            LazyColumn {
                items(peopleState.value){ person ->
                    val personImageUrl = peopleInSpaceViewModel.getMealImage(person.strMeal)
                    PersonView(personImageUrl, person, personSelected)
                }
            }
        }
    }
}


@Composable
fun PersonView(personImageUrl: String, person: Meal, personSelected : (person : Meal) -> Unit) {
    Row(
        modifier =  Modifier.fillMaxWidth().clickable(onClick = { personSelected(person) })
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        if (personImageUrl.isNotEmpty()) {
             CoilImage(data = personImageUrl, modifier = Modifier.size(60.dp), contentDescription = person.strMeal)
        } else {
            Spacer(modifier = Modifier.size(60.dp))
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(text = person.strMeal, style = TextStyle(fontSize = 20.sp))
            //Text(text = person.craft, style = TextStyle(color = Color.DarkGray, fontSize = 14.sp))
        }
    }
}

@Composable
fun PersonDetailsView(personName: String, popBack: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(personName) },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val person = peopleInSpaceViewModel.getMeal(personName)
            person?.let {
                Text(person.strMeal, style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.size(12.dp))

                val imageUrl = peopleInSpaceViewModel.getMealImage(person.strMeal)
                if (imageUrl.isNotEmpty()) {
                    CoilImage(data = imageUrl, modifier = Modifier.size(240.dp), contentDescription = person.strMeal)
                }

                Text("Category: "+person.strCategory, style = MaterialTheme.typography.h4)
                Text("Instructions: "+person.strInstructions)

                Spacer(modifier = Modifier.size(24.dp))

                // val bio = peopleInSpaceViewModel.getPersonBio(person.name)
                // Text(bio, style = MaterialTheme.typography.body1)
            }
        }
    }
}


