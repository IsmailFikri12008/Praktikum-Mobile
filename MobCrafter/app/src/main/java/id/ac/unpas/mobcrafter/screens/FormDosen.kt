package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
fun FormDosen(
    navController : NavHostController,
    id: String? = null,
    modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<PengelolaanDataDosenViewModel>()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"

    val nidn = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val gelarDepan = remember { mutableStateOf(TextFieldValue("")) }
    val gelarBelakang = remember { mutableStateOf(TextFieldValue("")) }

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
            value = gelarDepan.value,
            onValueChange = { gelarDepan.value = it },
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
            value = gelarBelakang.value,
            onValueChange = { gelarDepan.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "MT") }
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
            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                nidn.value.text,
                                nama.value.text,
                                gelarDepan.value.text,
                                gelarBelakang.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.update(id,
                                nidn.value.text,
                                nama.value.text,
                                gelarDepan.value.text,
                                gelarBelakang.value.text) } }
                    navController.navigate("pengelolaan-data-dosen")
                }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f),
                onClick = {
                    nidn.value = TextFieldValue("")
                    nama.value = TextFieldValue("")
                    gelarDepan.value = TextFieldValue("")
                    gelarBelakang.value = TextFieldValue("")
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
            viewModel.loadItem(id) { dataDosen -> dataDosen?.let {
                nidn.value = TextFieldValue(dataDosen.nidn)
                nama.value = TextFieldValue(dataDosen.nama)
                gelarDepan.value = TextFieldValue(dataDosen.gelarDepan)
                gelarBelakang.value = TextFieldValue(dataDosen.gelarBelakang)
            }
            }
        }
    }
}