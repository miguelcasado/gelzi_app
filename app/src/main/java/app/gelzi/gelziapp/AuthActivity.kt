package app.gelzi.gelziapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.gelzi.gelziapp.ui.screens.auth.LoginScreen
import app.gelzi.gelziapp.ui.screens.auth.RegisterScreen
import app.gelzi.gelziapp.ui.screens.auth.LoginViewModel
import com.example.gelzi.ui.theme.GelziTheme
import app.gelzi.gelziapp.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject lateinit var sessionManager: SessionManager

    // Registra el lanzador para el resultado de Google Sign-In
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            loginViewModel.handleGoogleSignInResult(result.data) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        } else {
            loginViewModel.setError("Error en el inicio de sesión con Google")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loginViewModel.initGoogleSignIn(this)

        setContent {
            GelziTheme {
                Surface {
                    val navController = rememberNavController()
                    // Scope para lanzar las transiciones con retardo
                    val coroutineScope = rememberCoroutineScope()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginScreen(
                                onNavigateToRegister = {
                                    // Se utiliza un pequeño retardo para evitar cambios de pantalla demasiado rápidos
                                    coroutineScope.launch {
                                        kotlinx.coroutines.delay(300)
                                        navController.navigate("register")
                                    }
                                },
                                onLoginSuccess = {
                                    startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                                    finish()
                                },
                                onLoginWithGoogle = {
                                    googleSignInLauncher.launch(loginViewModel.getGoogleSignInIntent())
                                }
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                                    finish()
                                },
                                onBackToLogin = {
                                    coroutineScope.launch {
                                        kotlinx.coroutines.delay(300)
                                        navController.popBackStack()
                                    }
                                },
                                onRegisterWithGoogle = {
                                    // Se puede utilizar el mismo flujo de Google Sign-In para el registro
                                    googleSignInLauncher.launch(loginViewModel.getGoogleSignInIntent())
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun markOnboardingCompleteAndNavigate(navController: androidx.navigation.NavHostController) {
        // Marca el onboarding como completado y navega a registro.
        // Usamos un scope de corutinas para llamar a la función suspend.
        lifecycleScope.launch {
            sessionManager.setOnboardingCompleted()
            navController.navigate("register") {
                popUpTo("onboarding") { inclusive = true }
            }
        }
    }
}
