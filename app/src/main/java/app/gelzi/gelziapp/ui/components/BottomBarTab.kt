package app.gelzi.gelziapp.ui.components
import androidx.compose.ui.graphics.Color
import app.gelzi.gelziapp.R
import app.gelzi.gelziapp.ui.navigation.Destinations

sealed class BottomBarTab(
    val title: String,
    val iconRes: Int,
    val color: Color,
    val route: String
) {
    object Calendar : BottomBarTab(
        title = "Calendar",
        iconRes = R.mipmap.thunder,
        color = Color(0xFFFFFFFF),
        route = Destinations.calendar
    )
    object Recipes : BottomBarTab(
        title = "Recipes",
        iconRes = R.mipmap.fork,
        color = Color(0xFFFFFFFF),
        route = Destinations.recipes
    )
    object Ai : BottomBarTab(
        title = "Ai",
        iconRes = R.mipmap.ai,
        color = Color(0xFFFFFFFF),
        route = Destinations.ai
    )
    object Exercises : BottomBarTab(
        title = "Exercises",
        iconRes = R.mipmap.exercise,
        color = Color(0xFFFFFFFF),
        route = Destinations.exercises
    )
    object Profile : BottomBarTab(
        title = "Profile",
        iconRes = R.mipmap.user,
        color = Color(0xFFFFFFFF),
        route = Destinations.profile
    )
}

val tabs = listOf(
    BottomBarTab.Calendar,
    BottomBarTab.Recipes,
    BottomBarTab.Ai,
    BottomBarTab.Exercises,
    BottomBarTab.Profile,
)
