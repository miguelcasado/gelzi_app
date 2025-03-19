package app.gelzi.gelziapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gelzi.ui.theme.Inter


@Composable
fun CategoriesSection() {
    Column {
        Text("Categorias", style = MaterialTheme.typography.titleLarge, fontFamily = Inter, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            item {
                CategoryCard("Pescado", Color(0xFFC8FAC8))
                Spacer(modifier = Modifier.width(16.dp))
                CategoryCard("Vegana", Color(0xFFFFF5C8))
                Spacer(modifier = Modifier.width(16.dp))
                CategoryCard("Carne", Color(0xFFFFC8C8))
                Spacer(modifier = Modifier.width(16.dp))
                CategoryCard("Sopas", Color(0xFFC8FAC8))
                Spacer(modifier = Modifier.width(16.dp))
                CategoryCard("Caldos", Color(0xFFFFF5C8))
                Spacer(modifier = Modifier.width(16.dp))
                CategoryCard("Carne", Color(0xFFFFC8C8))
            }}
    }
}