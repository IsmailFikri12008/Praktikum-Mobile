package id.ac.unpas.mobcrafter.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import id.ac.unpas.mobcrafter.R

enum class Menu(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home, Icons.Default.Home, "home"),

    PENGELOLAAN_MATAKULIAH(
        R.string.pengelolaan_matakuliah,
        Icons.Default.List,
        "pengelolaan-matakuliah"
    ),
    PENGELOLAAN_DOSEN(
        R.string.pengelolaan_dosen,
        Icons.Default.Settings,
        "pengelolaan-dosen");

    companion object {
        fun getTabFromResource(@StringRes resource: Int): Menu {
            return when (resource) {
                R.string.home -> HOME
                R.string.pengelolaan_matakuliah ->
                    PENGELOLAAN_MATAKULIAH
                else -> PENGELOLAAN_DOSEN
            }
        }
    }
}
