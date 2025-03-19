package app.gelzi.gelziapp.ui.screens.auth

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.gelzi.gelziapp.R
import app.gelzi.gelziapp.utils.SessionManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    // Nuevo método para manejar errores
    fun setError(message: String) {
        _uiState.value = _uiState.value.copy(error = message)
    }

    // Método para limpiar errores
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    // Configura el cliente de Google Sign-In
    fun initGoogleSignIn(activity: Activity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    // Obtiene el Intent para iniciar el flujo de Google Sign-In
    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    // Procesa el resultado de Google Sign-In
    fun handleGoogleSignInResult(data: Intent?, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).await()
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                val authResult = firebaseAuth.signInWithCredential(credential).await()

                authResult.user?.getIdToken(true)?.await()?.let { tokenResult ->
                    sessionManager.saveAuthToken(tokenResult.token!!)
                    onSuccess()
                } ?: run {
                    _uiState.value = _uiState.value.copy(error = "Error al obtener token")
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage)
            }
        }
    }

    // Método para iniciar sesión con email y contraseña
    fun loginWithEmailPassword(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val result = firebaseAuth
                    .signInWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
                    .await()

                val tokenResult = result.user?.getIdToken(true)?.await()
                val token = tokenResult?.token
                if (token != null) {
                    sessionManager.saveAuthToken(token)
                    onSuccess()
                } else {
                    _uiState.value = _uiState.value.copy(error = "No se pudo obtener el token.")
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage)
            }
        }
    }

    // Métodos para actualizar el estado del email y la contraseña
    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPass: String) {
        _uiState.value = _uiState.value.copy(password = newPass)
    }
    fun onErrorDismiss() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
