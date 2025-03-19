package app.gelzi.gelziapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import app.gelzi.gelziapp.ui.components.GlassmorphicBottomNavigation
import app.gelzi.gelziapp.ui.navigation.AppNavGraph
import app.gelzi.gelziapp.ui.navigation.Destinations
import app.gelzi.gelziapp.utils.SessionManager
import com.example.gelzi.ui.theme.GelziTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import app.gelzi.gelziapp.ui.screens.settings.SettingsScreen
import com.example.gelzi.ui.theme.Akira

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Primero comprobamos si existe token
        // Si no existe, redirigimos a AuthActivity
        // Esto se hace antes de setContent para no montar la UI de MainActivity innecesariamente.
        checkSession()

        enableEdgeToEdge()
        setContent {
            GelziTheme {
                MainScreen(sessionManager)
            }
        }
    }

    private fun checkSession() {
        // Revisa el token sincrónicamente usando coroutines
        // Podrías usar runBlocking o similar, pero mejor hacerlo de forma reactiva:
        // Para simplificar un ejemplo, se hace un “bloqueo” mínimo:
        val token = runBlocking {
            sessionManager.authToken.firstOrNull()
        }
        if (token.isNullOrEmpty()) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(sessionManager: SessionManager) {
    val hazeState = remember { HazeState() }
    val navController = rememberNavController()
    val currentScreen = remember { mutableStateOf(Destinations.calendar) }
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentScreen.value = destination.route ?: Destinations.calendar
        }
    }
    if (currentScreen.value=="settings"){
        Scaffold { innerPadding ->
            SettingsScreen(navController, innerPadding,sessionManager)
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("GELZI", fontFamily = Akira) },
                    scrollBehavior = topAppBarScrollBehavior,
                    actions = {
                        IconButton(onClick = { navController.navigate("settings") }) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings")
                        }
                    }
                )
            },
            bottomBar = {
                GlassmorphicBottomNavigation(
                    hazeState,
                    currentScreen.value
                ) { destination ->
                    navController.navigate(destination)
                }
            }
        ) { innerPadding ->
            //esto lo hago para que ignore el padding del bottom y asi el menu se vea transparente
            val layoutDirection = LocalLayoutDirection.current
            val adjustedPadding = PaddingValues(
                start = innerPadding.calculateStartPadding(layoutDirection),
                top = innerPadding.calculateTopPadding(),
                end = innerPadding.calculateEndPadding(layoutDirection),
                bottom = 0.dp
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                    .haze(
                        hazeState,
                        backgroundColor = MaterialTheme.colorScheme.background,
                        tint = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                        blurRadius = 30.dp
                    )
            ) {
                AppNavGraph(navController, adjustedPadding,sessionManager)
            }
        }
    }
}
