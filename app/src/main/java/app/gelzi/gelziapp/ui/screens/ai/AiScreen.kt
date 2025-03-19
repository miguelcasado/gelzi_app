    package app.gelzi.gelziapp.ui.screens.ai

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowForward
    import androidx.compose.material.icons.filled.PlayArrow
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.zIndex
    import app.gelzi.gelziapp.R
    import com.example.gelzi.ui.theme.Inter

    @Composable
    fun AiScreen(innerPaddingValues: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sección superior (asistente)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                // Título
                Text(
                    text = "Asistente IA",
                    fontSize = 20.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.TopStart)
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
                    painter = painterResource(id = R.mipmap.chatbot),
                    contentDescription = "Imagen",
                    modifier = Modifier
                        .size(400.dp)
                        .offset(x = (-160).dp, y = 100.dp) // Desplaza hacia afuera
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.BottomStart)
                )
            }

            // Sección inferior (asistente por voz)
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
                    text = "Asistente de voz",
                    fontSize = 20.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.TopStart)
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
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Reproducir",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Imagen que sobresale en la esquina inferior izquierda
                Image(
                    painter = painterResource(id = R.mipmap.ropeblue),
                    contentDescription = "Imagen",
                    modifier = Modifier
                        .size(400.dp)
                        .offset(x = (-160).dp, y = 100.dp) // Desplaza hacia afuera
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.BottomStart)
                )
            }
        }
    }
