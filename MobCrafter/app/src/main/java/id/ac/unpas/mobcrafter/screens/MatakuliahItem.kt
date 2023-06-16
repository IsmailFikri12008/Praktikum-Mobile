package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import id.ac.unpas.mobcrafter.model.Matakuliah
import id.ac.unpas.mobcrafter.ui.theme.teal2
import id.ac.unpas.mobcrafter.ui.theme.text1

@Composable
fun MatakuliahItem(
    item: Matakuliah,
    navController: NavHostController,
    onDelete: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()
    val ColorsButton = ButtonDefaults.buttonColors(
        backgroundColor = teal2,
        contentColor = text1
    )
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
                Text(text = "Kode: ")
                Text(text = item.kode)
            }
            Row {
                Text(text = "SKS: ")
                Text(text = "${item.sks}")
            }
            Row {
                Text(text = "Praktikum: ")
                Text(text = if (item.praktikum == 1) "YA" else "TIDAK")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = item.deskripsi,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("edit-pengelolaan-matakuliah/${item.id}") },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ColorsButton
                ) {
                    Text(text = "Edit")
                }
                Button(
                    onClick = { confirmationDialogState.show() },
                    colors = ColorsButton
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
