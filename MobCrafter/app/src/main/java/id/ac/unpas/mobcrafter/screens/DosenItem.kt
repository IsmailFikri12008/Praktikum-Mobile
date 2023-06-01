package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

@Composable
fun DosenItem(
    item: Dosen,
    navController: NavHostController,
    onDelete: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(2f)) {
                Text(text = "NIDN", fontSize = 12.sp)
                Text(
                    text = item.nidn, fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(text = "Nama", fontSize = 12.sp)
                Text(
                    text = item.nama, fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(text = "Gelar Depan", fontSize = 12.sp)
                Text(
                    text = item.gelar_depan, fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(text = "Gelar Belakang", fontSize = 12.sp)
                Text(
                    text = item.gelar_belakang, fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(text = "Pendidikan", fontSize = 12.sp)
                Text(text = item.pendidikan.name, fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                Icons.Default.MoreVert,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(8.dp)
                    .weight(1f, true)
                    .clickable {
                        expanded = true
                    },
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = (-66).dp, y = (-10).dp)
        ) {
            subMenus.forEachIndexed { _, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    when (s) {
                        "Edit" -> {
                            navController.navigate("edit-pengelolaan-dosen/${item.id}")
                        }
                        "Delete" -> {
                            confirmationDialogState.show()
                        }
                    }
                }) {
                    Text(text = s)
                }
            }
        }
    }
    Divider(modifier = Modifier.fillMaxWidth())
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


