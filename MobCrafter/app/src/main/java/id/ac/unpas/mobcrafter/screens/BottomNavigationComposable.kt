package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.ac.unpas.mobcrafter.ui.theme.Bar
import id.ac.unpas.mobcrafter.ui.theme.teal1

@Composable
fun BottomNavigationComposable(title: String, onClick: (Menu) -> Unit) {
    val tabs = Menu.values()
    BottomNavigation(
        elevation = 15.dp, backgroundColor = teal1
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                selected = tab.name == title,
                onClick = {
                    onClick(tab)
                },
                icon = {
                    Icon(
                        tab.icon,
                        modifier =
                        Modifier
                            .height(32.dp)
                            .width(32.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                selectedContentColor =
                LocalContentColor.current,
                unselectedContentColor =
                LocalContentColor.current,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}