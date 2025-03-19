package app.gelzi.gelziapp.ui.screens.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.gelzi.gelziapp.R

@Composable
fun ProfileScreen(innerPaddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Fondo oscuro
            .padding(innerPaddingValues)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProfileHeader()
            }

            item {
                StatsRow()
            }

            item {
                EvolutionCard()
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),  // Esquinas redondeadas
        colors = CardDefaults.cardColors(containerColor = Color.White),  // Color de fondo blanco para el card
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),  // Padding interno para el contenido
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen circular del usuario (por ahora, un fondo gris)
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.rick),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Marcos Pérez",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                    // Edad
                Row {
                    Text(
                        text = "Miembro: ",
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                        Text(text = "Básico", fontSize = 12.sp, color = Color.Gray)

                }

            }
        }
    }
}


@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatsCard(
            icon = Icons.Default.PlayArrow, // Icono de reloj
            title = "Duration",
            value = "16:01",
            iconColor = Color(0xFFFFDD00)
        )

        StatsCard(
            icon = Icons.Default.AddCircle, // Icono de pesas
            title = "Sessions",
            value = "5",
            iconColor = Color(0xFFFFDD00)
        )
        StatsCard(
            icon = Icons.Default.FavoriteBorder, // Icono de pesas
            title = "Calories",
            value = "254",
            iconColor = Color(0xFFFFDD00)
        )
    }
}

@Composable
fun StatsCard(icon: ImageVector, title: String, value: String, iconColor: Color) {
    Card(
        modifier = Modifier
            .width(115.dp)
            .height(140.dp),
        shape = RoundedCornerShape(24.dp), // Esquinas más redondeadas
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Fondo oscuro
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icono personalizado con color verde
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(iconColor.copy(alpha = 0.2f), shape = CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = value,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}
@Composable
fun EvolutionCard() {
    val months = listOf("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Evolución",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Contenedor del gráfico con scroll horizontal
            Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                Column {
                    Row {
                        // Columna de eje Y (porcentajes)
                        Column(
                            modifier = Modifier
                                .width(40.dp)
                                .height(200.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("100", fontSize = 10.sp, color = Color.Black)
                            Text("75", fontSize = 10.sp, color = Color.Black)
                            Text("50", fontSize = 10.sp, color = Color.Black)
                            Text("25", fontSize = 10.sp, color = Color.Black)
                            Text("0", fontSize = 10.sp, color = Color.Black)
                        }

                        // Box con el gráfico
                        BarChart(
                            data = listOf(10, 20, 15, 30, 25, 40, 60, 55, 20, 30, 10, 5),
                            maxValue = 100,
                            months = months,
                            modifier = Modifier
                                .width(600.dp)  // Ancho suficiente para todos los meses
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun BarChart(
    data: List<Int>,
    maxValue: Int,
    months: List<String>,
    modifier: Modifier = Modifier
) {
    // 1. Configuración de dimensiones
    val totalWidth = 600.dp
    val barCount = data.size
    val slotWidthPx = remember { mutableStateOf(0f) } // Almacenaremos el ancho real en píxeles

    Column(modifier = modifier.width(totalWidth)) {
        // 2. Canvas que mide su propio ancho
        Canvas(
            modifier = Modifier
                .width(totalWidth)
                .height(200.dp)
                .onSizeChanged { size ->
                    slotWidthPx.value = size.width.toFloat() / barCount
                }
        ) {
            val barWidth = slotWidthPx.value * 0.6f
            val scaleFactor = size.height / maxValue.toFloat()

            data.forEachIndexed { index, value ->
                val barHeight = value * scaleFactor
                val left = index * slotWidthPx.value + (slotWidthPx.value - barWidth) / 2
                val top = size.height - barHeight

                drawRoundRect(
                    color = Color(0xFF32CD32),
                    topLeft = Offset(left, top),
                    size = Size(barWidth, barHeight),
                    cornerRadius = CornerRadius(8.dp.toPx())
                )
            }
        }

        // 3. Fila de meses sincronizada con el Canvas
        Row(
            modifier = Modifier
                .width(totalWidth)
                .padding(top = 8.dp)
                .border(1.dp, Color.Red),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            months.forEach { month ->
                Box(
                    modifier = Modifier
                        .width(with(LocalDensity.current) { (slotWidthPx.value * 0.8f).toDp() })
                        .padding(horizontal = 1.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = month,
                        color = Color.Black.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(40.dp)
                    )
                }
            }
        }
    }
}
