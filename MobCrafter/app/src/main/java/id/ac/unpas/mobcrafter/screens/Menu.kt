package id.ac.unpas.mobcrafter.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import id.ac.unpas.mobcrafter.R

enum class Menu(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    BERANDA(
        R.string.beranda,
        Icons.Default.Menu,
        "beranda"),
    PENGELOLAAN_MAHASISWA(
        R.string.pengelolaan_mahasiswa,
        Icons.Default.Person,
        "pengelolaan-mahasiswa"),
    PENGELOLAAN_MATAKULIAH(
        R.string.pengelolaan_matakuliah,
        Icons.Default.CollectionsBookmark,
        "pengelolaan-matakuliah"
    ),
    PENGELOLAAN_DOSEN(
        R.string.pengelolaan_dosen,
        Icons.Default.SupervisorAccount,
        "pengelolaan-dosen");


    companion object {
        fun getTabFromResource(@StringRes resource: Int): Menu {
            return when (resource) {
                R.string.beranda ->
                    BERANDA
                R.string.pengelolaan_mahasiswa ->
                    PENGELOLAAN_MAHASISWA
                R.string.pengelolaan_matakuliah ->
                    PENGELOLAAN_MATAKULIAH
                else -> PENGELOLAAN_DOSEN
            }
        }
    }
}
