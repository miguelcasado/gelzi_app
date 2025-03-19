package app.gelzi.gelziapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(searchText: String, onSearchChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchChange,
        placeholder = { Text("Buscar recetas...", modifier = Modifier.padding(start = 16.dp)) },
        shape = RoundedCornerShape(60.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Buscar",
                tint = Color.Gray,
                modifier = Modifier.padding(end = 16.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    )
}
