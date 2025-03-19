package app.gelzi.gelziapp.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsSection(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val route: String
)

val sections = listOf(
    SettingsSection(
        icon = Icons.Default.Settings,
        title = "General",
        description = "Idioma, Tema, Tamaño de fuente",
        route = "general_settings"
    ),
    SettingsSection(
        icon = Icons.Default.Person,
        title = "Cuenta",
        description = "Perfil, Cambiar contraseña, Redes sociales",
        route = "cuenta_settings"
    ),
    SettingsSection(
        icon = Icons.Default.Notifications,
        title = "Notificaciones",
        description = "Push, Sonido, Vibración",
        route = "notificaciones_settings"
    ),
    SettingsSection(
        icon = Icons.Default.Settings,
        title = "Privacidad",
        description = "Permisos, Datos compartidos, Historial y cookies",
        route = "privacidad_settings"
    ),
    SettingsSection(
        icon = Icons.Default.Settings,
        title = "Seguridad",
        description = "Bloqueo (PIN/biometría), Autenticación en dos pasos",
        route = "seguridad_settings"
    ),
    SettingsSection(
        icon = Icons.Default.Star,
        title = "Membresías",
        description = "Plan, Beneficios, Pagos, Renovación",
        route = "membresias_settings"
    ),
    SettingsSection(
        icon = Icons.Default.Face,
        title = "IA",
        description = "Asistente, Personalización, Retroalimentación",
        route = "ia_settings"
    )
)