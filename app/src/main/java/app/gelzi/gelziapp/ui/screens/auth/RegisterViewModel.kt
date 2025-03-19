package app.gelzi.gelziapp.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.gelzi.gelziapp.utils.SessionManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPass: String) {
        _uiState.value = _uiState.value.copy(password = newPass)
    }

    fun registerWithEmailPassword(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val result = firebaseAuth
                    .createUserWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
                    .await()

                // Obtenemos el token
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

    // Método para limpiar el error
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
