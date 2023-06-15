package id.ac.unpas.mobcrafter.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import id.ac.unpas.mobcrafter.model.JenisKelamin
import id.ac.unpas.mobcrafter.ui.theme.Purple700
import id.ac.unpas.mobcrafter.ui.theme.Teal200
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun FormPencatatanMahasiswaScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    id: String? = null
){
    val viewModel = hiltViewModel<PengelolaanMahasiswaViewModel>()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"

    val npm = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal_lahir = remember { mutableStateOf(TextFieldValue("")) }
    val jenis_kelamin = remember { mutableStateOf(JenisKelamin.LAKI_LAKI) }
    val jenisKelaminOptions = listOf(JenisKelamin.LAKI_LAKI,JenisKelamin.PEREMPUAN)
    val tanggalDialogState = rememberMaterialDialogState()
    val isDropdownOpen = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            label = { Text(text = "NPM") },
            value = npm.value,
            onValueChange = { npm.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "321123453") }
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
            label = { Text(text = "Tanggal Lahir") },
            value = tanggal_lahir.value,
            onValueChange = {
                tanggal_lahir.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    tanggalDialogState.show()
                },
            placeholder = { Text(text = "yyyy-mm-dd") },
            enabled = false
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = TextFieldValue(text = jenis_kelamin.value.name),
                onValueChange = { },
                label = { Text(text = "Tanggal Lahir") },
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
                jenisKelaminOptions.forEach { pilihanJenisKelamin ->
                    DropdownMenuItem(
                        onClick = {
                            jenis_kelamin.value = pilihanJenisKelamin
                            isDropdownOpen.value = false
                        }
                    ) {
                        Text(
                            text = pilihanJenisKelamin.name,
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
                                npm.value.text,
                                nama.value.text,
                                tanggal_lahir.value.text,
                                jenis_kelamin.value
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                npm.value.text,
                                nama.value.text,
                                tanggal_lahir.value.text,
                                jenis_kelamin.value
                            )
                        }
                    }
                    navController.navigate("pengelolaan-mahasiswa")
                }, colors = loginButtonColors
            ){
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
                    npm.value = TextFieldValue("")
                    nama.value = TextFieldValue("")
                    tanggal_lahir.value = TextFieldValue("")
                    jenis_kelamin.value = JenisKelamin.LAKI_LAKI
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
    MaterialDialog(dialogState = tanggalDialogState, buttons = {
        positiveButton("OK")
        negativeButton("Batal")
    }) {
        datepicker { date ->
            tanggal_lahir.value = TextFieldValue(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { mahasiswa ->
                mahasiswa?.let {
                    npm.value = TextFieldValue(mahasiswa.npm)
                    nama.value = TextFieldValue(mahasiswa.nama)
                    tanggal_lahir.value = TextFieldValue(mahasiswa.tanggal_lahir)
                    jenis_kelamin.value = mahasiswa.jenis_kelamin
                }
            }
        }
    }

}