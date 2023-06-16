package id.ac.unpas.mobcrafter.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import id.ac.unpas.mobcrafter.ui.theme.*
import androidx.compose.ui.unit.*


//@Composable
//fun Beranda() {
//    Box(modifier = Modifier
//        .background(BgHome)
//        .fillMaxSize()
//    ){Column {
//        Text(text = "Selamat di aplikasi ini")
//    }
//    }
//}
@ExperimentalFoundationApi
@Composable
fun Beranda(
    navController: NavHostController,
    id: String? = null,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white1)
    ) {
        Column {
            GreetingSection()
            DaftarMenu(navController = navController)
            Text(
                text = "© 2023 MobCrafter",
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}


@Composable
fun GreetingSection(
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Selamat Datang Di Aplikasi",
                fontWeight = FontWeight.Bold,
                fontSize = 29.sp,
                modifier = Modifier.padding(bottom = 8.dp)

            )
            Text(
                text = "Pengelolaan Perkuliahan",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun DaftarMenu(
    navController: NavHostController,
    id: String? = null,
    modifier: Modifier = Modifier,
    color: Color = teal2,
) {
    val ColorsButton = ButtonDefaults.buttonColors(
        backgroundColor = teal3,
        contentColor = text1
    )

    Column(modifier = modifier.padding(15.dp)) {
        // Bagian Mahasiswa
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(0.75f)
                ) {
                    Text(
                        text = "Mahasiswa",
                        fontWeight = FontWeight.SemiBold,
                        color = text1,
                        fontSize = 25.sp
                    )
                    Text(
                        text = "Data Keseluruhan Mahasiswa",
                        fontWeight = FontWeight.Normal,
                        color = text2,
                        fontSize = 15.sp
                    )
                }
                Button(
                    onClick = {
                        navController.navigate("pengelolaan-mahasiswa")
                    },
                    colors = ColorsButton,
                    modifier = Modifier.weight(0.25f).clip(RoundedCornerShape(25.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        // Bagian Matakuliah
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(0.75f)
                ) {
                    Text(
                        text = "Matakuliah",
                        fontWeight = FontWeight.SemiBold,
                        color = text1,
                        fontSize = 25.sp
                    )
                    Text(
                        text = "Data Keseluruhan Matakuliah",
                        fontWeight = FontWeight.Normal,
                        color = text2,
                        fontSize = 15.sp
                    )
                }
                Button(
                    onClick = {
                        navController.navigate("pengelolaan-matakuliah")
                    },
                    colors = ColorsButton,
                    modifier = Modifier.weight(0.25f).clip(RoundedCornerShape(25.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        // Bagian Dosen
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(0.75f)
                ) {
                    Text(
                        text = "Dosen",
                        fontWeight = FontWeight.SemiBold,
                        color = text1,
                        fontSize = 25.sp
                    )
                    Text(
                        text = "Data Keseluruhan Dosen",
                        fontWeight = FontWeight.Normal,
                        color = text2,
                        fontSize = 15.sp
                    )
                }
                Button(
                    onClick = {
                        navController.navigate("pengelolaan-dosen")
                    },
                    colors = ColorsButton,
                    modifier = Modifier.weight(0.25f).clip(RoundedCornerShape(25.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}