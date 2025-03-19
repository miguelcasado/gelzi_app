package app.gelzi.gelziapp.ui.screens.settings

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import app.gelzi.gelziapp.AuthActivity
import app.gelzi.gelziapp.data.model.sections
import app.gelzi.gelziapp.utils.SessionManager
import com.example.gelzi.ui.theme.Akira
import com.example.gelzi.ui.theme.Inter
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavHostController, innerPadding: PaddingValues, sessionManager: SessionManager) {
    val iconColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Centrar elementos verticalmente
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, // Flecha en vez de cruz
                contentDescription = "Volver",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.popBackStack() }, // Regresar al hacer clic
                tint = iconColor
            )
            Spacer(modifier = Modifier.width(8.dp)) // Espacio entre la flecha y el título
            Text("Ajustes", style = MaterialTheme.typography.headlineLarge, fontFamily = Inter)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(sections) { section ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { navController.navigate(section.route) }
                        .clip(RoundedCornerShape(5.dp)),
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colorScheme.surface
                ) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = section.icon,
                            contentDescription = section.title,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = section.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black,
                                fontFamily = Inter
                            )
                            Text(
                                text = section.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.DarkGray
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Ir a ${section.title}",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Divider()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                LogoutButton(navController = navController, sessionManager = sessionManager)
            }
        }
    }
}

@Composable
fun LogoutButton(
    navController: NavHostController,
    sessionManager: SessionManager
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            coroutineScope.launch {
                sessionManager.clearAuthToken()
                val intent = Intent(context, AuthActivity::class.java)
                context.startActivity(intent)
                (context as? Activity)?.finish()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = "Logout",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Cerrar Sesión", color = Color.White)
    }
}
