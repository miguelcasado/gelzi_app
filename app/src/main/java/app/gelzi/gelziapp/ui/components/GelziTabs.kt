package app.gelzi.gelziapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GelziTabs(
    tabs: List<String>,
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tabs.size) { index ->
            val tab = tabs[index]
            val isSelected = tab == selectedTab

            // El color de fondo depende de si está seleccionado o no
            val backgroundColor =
                if (isSelected) Color(0xFF000000)
                else Color(0xFFEEEEEE)

            // El color de texto también cambia
            val textColor =
                if (isSelected) Color.White
                else Color.Black

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .background(backgroundColor, shape = RoundedCornerShape(32.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .then(
                        Modifier
                            .clickable { onTabSelected(tab) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = textColor,
                    fontSize = 14.sp
                )
            }
        }
    }
}
