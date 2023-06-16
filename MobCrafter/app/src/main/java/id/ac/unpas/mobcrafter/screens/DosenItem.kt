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
import id.ac.unpas.mobcrafter.model.Dosen
import id.ac.unpas.mobcrafter.ui.theme.teal2
import id.ac.unpas.mobcrafter.ui.theme.text1

@Composable
fun DosenItem(
    item: Dosen,
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
                Text(text = "NIDN: ")
                Text(text = item.nidn)
            }
            Row {
                Text(text = "Gelar Depan: ")
                Text(text = item.gelar_depan)
            }
            Row {
                Text(text = "Gelar Belakang: ")
                Text(text = item.gelar_belakang)
            }
            Row {
                Text(text = "Pendidikan: ")
                Text(text = item.pendidikan.name)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("edit-pengelolaan-dosen/${item.id}") },
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
            text = "Apakah anda yakin ingin menghapus data ?")
    }
}


