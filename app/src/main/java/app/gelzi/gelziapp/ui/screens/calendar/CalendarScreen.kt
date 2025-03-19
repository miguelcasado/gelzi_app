package app.gelzi.gelziapp.ui.screens.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.gelzi.gelziapp.R
import app.gelzi.gelziapp.ui.components.InfiniteWeekRowCalendar
import com.example.gelzi.ui.theme.Inter
import java.time.LocalDate
import kotlin.random.Random


@Composable
fun CalendarScreen(innerPaddingValues: PaddingValues) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE / 2)
    val progressPercentage by remember { mutableStateOf(Random.nextInt(1, 101)) }

    LazyColumn(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxWidth()
    ) {
        item {
            InfiniteWeekRowCalendar(
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it },
                listState = listState
            )
            Spacer(modifier = Modifier.height(15.dp))
            // Título y barra de progreso
            val formattedDate = selectedDate.format(java.time.format.DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                Text(
                    text = formattedDate,
                    fontSize = 20.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Sección superior (asistente)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .shadow(8.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    // Título
                    Text(
                        text = "Rutina",
                        fontSize = 20.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.TopStart)
                    )

                    // Porcentaje en la esquina superior
                    Text(
                        text = "$progressPercentage% completado hoy",
                        fontSize = 12.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )

                    // Botón en la esquina inferior derecha
                    IconButton(
                        onClick = { /* Acción del botón */ },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(50)
                            )
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Continuar",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // Imagen que sobresale en la esquina inferior izquierda
                    Image(
                        painter = painterResource(id = R.mipmap.mancuerna),
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .size(400.dp)
                            .offset(x = (-120).dp, y = 70.dp) // Desplaza hacia afuera
                            .clip(RoundedCornerShape(8.dp))
                            .align(Alignment.BottomStart)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .shadow(6.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    // Título
                    Text(
                        text = "Dieta",
                        fontSize = 20.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.TopStart)
                    )

                    // Porcentaje en la esquina superior
                    Text(
                        text = "$progressPercentage% completado hoy",
                        fontSize = 12.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )

                    // Botón en la esquina inferior derecha
                    IconButton(
                        onClick = { /* Acción del botón */ },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(50)
                            )
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Continuar",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // Imagen que sobresale en la esquina inferior izquierda
                    Image(
                        painter = painterResource(id = R.mipmap.frutas),
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .size(400.dp)
                            .offset(x = (-120).dp, y = 70.dp) // Desplaza hacia afuera
                            .clip(RoundedCornerShape(8.dp))
                            .align(Alignment.BottomStart)
                    )
                }
            }
        }
    }
}

