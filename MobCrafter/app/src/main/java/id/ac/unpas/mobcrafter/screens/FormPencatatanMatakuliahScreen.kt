package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.mobcrafter.ui.theme.Purple700
import id.ac.unpas.mobcrafter.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanMatakuliahScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    id: String? = null
) {
    val viewModel = hiltViewModel<PengelolaanMatakuliahViewModel>()
    val kode = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val sks = remember { mutableStateOf(TextFieldValue("")) }
    var praktikum by remember { mutableStateOf(false) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..."
    else "Simpan"
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Kode") },
            value = kode.value,
            onValueChange = {
                kode.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "kode matakuliah") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "nama mata kuliah") }
        )
        OutlinedTextField(
            label = { Text(text = "sks") },
            value = sks.value,
            onValueChange = {
                sks.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "jumlah sks") }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = "Praktikum",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 8.dp)
            )
            Checkbox(
                checked = praktikum,
                onCheckedChange = {
                    praktikum = it
                }
            )
        }
        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Deskripsi Matakuliah") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val praktikumInt : Int
                praktikumInt = if(praktikum) {
                    1
                } else {
                    0
                }
                if (id == null) {
                    scope.launch {
                        viewModel.insert(
                            kode.value.text,
                            nama.value.text,
                            sks.value.text.toByte(),
                            praktikumInt,
                            deskripsi.value.text
                        )
                    }
                } else {
                    scope.launch {
                        viewModel.update(
                            id,
                            kode.value.text,
                            nama.value.text,
                            sks.value.text.toByte(),
                            praktikumInt,
                            deskripsi.value.text
                        )
                    }
                }
                navController.navigate("pengelolaan-matakuliah")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                kode.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                sks.value = TextFieldValue("")
                praktikum = false
                deskripsi.value = TextFieldValue()
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }
    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { matakuliah ->
                matakuliah?.let {
                    kode.value = TextFieldValue(matakuliah.kode)
                    nama.value = TextFieldValue(matakuliah.nama)
                    sks.value = TextFieldValue(matakuliah.sks.toString())
                    praktikum = matakuliah.praktikum == 1
                    deskripsi.value = TextFieldValue(matakuliah.deskripsi)
                }
            }
        }
    }
}