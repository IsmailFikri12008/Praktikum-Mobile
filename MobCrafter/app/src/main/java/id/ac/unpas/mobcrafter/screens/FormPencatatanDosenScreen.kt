package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.mobcrafter.model.Pendidikan
import id.ac.unpas.mobcrafter.ui.theme.Purple700
import id.ac.unpas.mobcrafter.ui.theme.Teal200
import kotlinx.coroutines.launch



@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun FormPencatatanDosenScreen(
    navController: NavHostController,
    id: String? = null,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<PengelolaanDosenViewModel>()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"

    val nidn = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val gelar_depan = remember { mutableStateOf(TextFieldValue("")) }
    val gelar_belakang = remember { mutableStateOf(TextFieldValue("")) }
    val pendidikan = remember { mutableStateOf(Pendidikan.S2) }

    val pendidikanOptions = listOf(Pendidikan.S2, Pendidikan.S3)

    val isDropdownOpen = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            label = { Text(text = "NIDN") },
            value = nidn.value,
            onValueChange = { nidn.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "321123453213") }
        )

        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = { nama.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "AAAAAA") }
        )

        OutlinedTextField(
            label = { Text(text = "Gelar Depan") },
            value = gelar_depan.value,
            onValueChange = { gelar_depan.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "ST") }
        )

        OutlinedTextField(
            label = { Text(text = "Gelar Belakang") },
            value = gelar_belakang.value,
            onValueChange = { gelar_belakang.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "MT") }
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = TextFieldValue(text = pendidikan.value.name),
                onValueChange = { },
                label = { Text(text = "Pendidikan") },
                readOnly = true,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { isDropdownOpen.value = true }
            )

            DropdownMenu(
                expanded = isDropdownOpen.value,
                onDismissRequest = { isDropdownOpen.value = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                pendidikanOptions.forEach { pilihanPendidikan ->
                    DropdownMenuItem(
                        onClick = {
                            pendidikan.value = pilihanPendidikan
                            isDropdownOpen.value = false
                        }
                    ) {
                        Text(
                            text = pilihanPendidikan.name,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

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
            Button(
                modifier = Modifier.weight(5f), onClick = {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                nidn.value.text,
                                nama.value.text,
                                gelar_depan.value.text,
                                gelar_belakang.value.text,
                                pendidikan.value
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                nidn.value.text,
                                nama.value.text,
                                gelar_depan.value.text,
                                gelar_belakang.value.text,
                                pendidikan.value
                            )
                        }
                    }
                    navController.navigate("pengelolaan-dosen")
                }, colors = loginButtonColors
            ) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    nidn.value = TextFieldValue("")
                    nama.value = TextFieldValue("")
                    gelar_depan.value = TextFieldValue("")
                    gelar_belakang.value = TextFieldValue("")
                    pendidikan.value = Pendidikan.S2
                },
                colors = resetButtonColors
            ) {
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
            viewModel.loadItem(id) { dosen ->
                dosen?.let {
                    nidn.value = TextFieldValue(dosen.nidn)
                    nama.value = TextFieldValue(dosen.nama)
                    gelar_depan.value = TextFieldValue(dosen.gelar_depan)
                    gelar_belakang.value = TextFieldValue(dosen.gelar_belakang)
                    pendidikan.value = dosen.pendidikan
                }
            }
        }
    }
}
