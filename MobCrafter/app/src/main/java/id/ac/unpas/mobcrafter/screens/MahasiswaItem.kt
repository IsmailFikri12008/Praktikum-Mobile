package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import id.ac.unpas.mobcrafter.model.Mahasiswa
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun MahasiswaItem(
    item: Mahasiswa,
    navController: NavHostController,
    onDelete: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.nama,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "NPM: ")
                Text(text = item.npm)
            }
            Row {
                Text(text = "Tanggal Lahir: ")
                Text(text = dateFormat.format(item.tanggal_lahir))
            }
            Row {
                Text(text = "Jenis Kelamin: ")
                Text(text = item.jenis_kelamin.name)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("edit-pengelolaan-mahasiswa/${item.id}") },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = "Edit")
                }
                Button(
                    onClick = { confirmationDialogState.show() }
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }

    MaterialDialog(dialogState = confirmationDialogState,
        buttons = {
            positiveButton("Ya", onClick = {
                onDelete(item.id)
            })
            negativeButton("Tidak")
        }) {
        title(text = "Konfirmasi")
        message(
            text = "Apakah anda yakin ingin menghapus data ?"
        )
    }
}


