package app.gelzi.gelziapp.ui.screens.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.gelzi.gelziapp.R
import app.gelzi.gelziapp.ui.components.CategoriesSection
import app.gelzi.gelziapp.ui.components.SmallCard
import app.gelzi.gelziapp.ui.components.GelziTabs
import app.gelzi.gelziapp.ui.components.SearchBar
import com.example.gelzi.ui.theme.Inter

@Composable
fun ExercisesScreen(innerPaddingValues: PaddingValues){
    var searchText by remember { mutableStateOf("") }
    // Controla el tab seleccionado
    var selectedTab by remember { mutableStateOf("Todos") }

    // Ejemplo de lista de tabs
    val tabs = listOf("Todos", "Rápidas", "Fáciles", "Light", "Proteicas", "Favoritas")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
        item {
            SearchBar(searchText) { searchText = it }
        }
        item {
            CategoriesSection()
        }
        item {
            Column {
                Text(
                    "Descubrir",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = Inter,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        // Aquí añadimos la fila de “tabs” (chips) de YouTube
        item {
            GelziTabs(
                tabs = tabs,
                selectedTab = selectedTab
            ) { newTab ->
                selectedTab = newTab
            }
        }
        items(20) {
            Row(modifier = Modifier.fillMaxWidth()) {
                SmallCard(
                    imageRes = R.mipmap.chica,
                    title = "Tren Superior",
                    instructor = "Pecho, Espalda, Triceps",
                    progress = 0.81f,
                    time = "120 min",
                    onFavoriteChange = { isFavorite -> println("Es favorito: $isFavorite") },
                    onClick = { println("Ejercicio seleccionado") },
                    modifier = Modifier
                        .weight(1f) // Ocupa la mitad del ancho
                        .padding(8.dp) // Espacio entre las tarjetas
                )
                SmallCard(
                    imageRes = R.mipmap.chica,
                    title = "Tren Superior",
                    instructor = "Pecho, Espalda, Triceps aaaaaaaaaaaaaa",
                    progress = 0.81f,
                    time = "120 min",
                    onFavoriteChange = { isFavorite -> println("Es favorito: $isFavorite") },
                    onClick = { println("Ejercicio seleccionado") },
                    modifier = Modifier
                        .weight(1f) // Ocupa la mitad del ancho
                        .padding(8.dp) // Espacio entre las tarjetas
                )
            }
        }
    }
}