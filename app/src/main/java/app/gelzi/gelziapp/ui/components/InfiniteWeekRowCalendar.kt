package app.gelzi.gelziapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gelzi.ui.theme.Akira
import com.example.gelzi.ui.theme.Inter
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun InfiniteWeekRowCalendar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    listState: androidx.compose.foundation.lazy.LazyListState
) {
    val currentDate = LocalDate.now()

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 6.dp)
    ) {
        items(Int.MAX_VALUE, key = { it }) { index ->
            val date = currentDate.plusDays(index - (Int.MAX_VALUE / 2).toLong())
            val backgroundColor = when {
                date == selectedDate -> Color(0xFFFFEA00)
                date == currentDate -> Color.Gray
                else -> Color.LightGray
            }

            Column(
                modifier = Modifier
                    .width(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor)
                    .clickable { onDateSelected(date) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase(),
                    fontSize = 10.sp,
                    fontFamily = Inter,
                    color = Color.White,
                    maxLines = 1
                )
                // Día del mes en número
                Text(
                    text = date.dayOfMonth.toString(),
                    fontSize = 16.sp,
                    fontFamily = Akira,
                    fontWeight = if (date == selectedDate) FontWeight.Bold else FontWeight.Normal,
                    color = Color.White,
                    maxLines = 1
                )

                // Mes en tres letras en mayúscula
                Text(
                    text = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase(),
                    fontSize = 10.sp,
                    fontFamily = Inter,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}
