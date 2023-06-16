package id.ac.unpas.mobcrafter.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.mobcrafter.model.Mahasiswa
import id.ac.unpas.mobcrafter.ui.theme.teal2
import id.ac.unpas.mobcrafter.ui.theme.teal3
import id.ac.unpas.mobcrafter.ui.theme.text1
import kotlinx.coroutines.launch

@Composable
fun PengelolaanMahasiswaScreen(navController : NavHostController,
                               modifier: Modifier = Modifier,
                               snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel = hiltViewModel<PengelolaanMahasiswaViewModel>()
    val items: List<Mahasiswa> by
    viewModel.list.observeAsState(initial = listOf())
    val ColorsButton = ButtonDefaults.buttonColors(
        backgroundColor = teal2,
        contentColor = text1
    )
    Column(modifier = modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pengelolaan-mahasiswa")
        }, colors = ColorsButton) {
            Text(text = "Tambah")
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                MahasiswaItem(
                    item = item, navController =
                    navController
                ) {
                    scope.launch {
                        viewModel.delete(it)
                    }
                }
            })
        }
    }

    LaunchedEffect(scope) {
        viewModel.loadItems()
    }
    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }
    viewModel.toast.observe(LocalLifecycleOwner.current) {
        scope.launch {
            snackbarHostState.showSnackbar(it, actionLabel =
            "Tutup", duration = SnackbarDuration.Long)
        }
    }
}