package app.gelzi.gelziapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gelzi.ui.theme.Inter

@Composable
fun CategoryCard(title: String, bgColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(16.dp)
            .height(120.dp)
            .width(80.dp)
    ) {
        Text(
            text = title,
            fontFamily = Inter,
            fontSize = 13.sp,
            maxLines = 1, // Limita el texto a una línea
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis, // Muestra '...' si el texto es demasiado largo
            modifier = Modifier.fillMaxWidth() // Asegura que el texto se ajuste al ancho de la card
        )
    }
}