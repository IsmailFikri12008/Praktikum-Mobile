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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import id.ac.unpas.mobcrafter.ui.theme.*


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
            .background(BgHome)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            DaftarMenu(navController = navController)
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
                text = "Selamat Datang Di Aplikasi"
            )
            Text(
                text = "Pengelolaan Mahasiswa"
            )
        }
    }
}

@Composable
fun DaftarMenu(
    navController: NavHostController,
    id: String? = null,
    modifier: Modifier = Modifier,
    color: Color = Purple700,
) {
    val ColorsButton = ButtonDefaults.buttonColors(
        backgroundColor = Teal200,
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
                    modifier = Modifier.weight(0.7f)
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
                    modifier = Modifier.weight(0.3f).clip(RoundedCornerShape(25.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Icon",
                        modifier = Modifier.padding(8.dp)
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
                    modifier = Modifier.weight(0.7f)
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
                    modifier = Modifier.weight(0.3f).clip(RoundedCornerShape(25.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Icon",
                        modifier = Modifier.padding(8.dp)
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
                    modifier = Modifier.weight(0.7f)
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
                    modifier = Modifier.weight(0.3f).clip(RoundedCornerShape(25.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Icon",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}













//
//@ExperimentalFoundationApi
//@Composable
//fun FeatureSection(features: List<Feature>) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//        Text(
//            text = "Features",
//            style = MaterialTheme.typography.h1,
//            modifier = Modifier.padding(15.dp)
//        )
//        LazyVerticalGrid(
//            cells = GridCells.Fixed(2),
//            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
//            modifier = Modifier.fillMaxHeight()
//        ) {
//            items(features.size) {
//                FeatureItem(feature = features[it])
//            }
//        }
//    }
//}
//
//@Composable
//fun FeatureItem(
//    feature: Feature
//) {
//    BoxWithConstraints(
//        modifier = Modifier
//            .padding(7.5.dp)
//            .aspectRatio(1f)
//            .clip(RoundedCornerShape(10.dp))
//            .background(feature.darkColor)
//    ) {
//        val width = constraints.maxWidth
//        val height = constraints.maxHeight
//
//        // Medium colored path
//        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
//        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
//        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
//        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
//        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())
//
//        val mediumColoredPath = Path().apply {
//            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
//            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
//            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
//            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
//            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
//            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
//            lineTo(-100f, height.toFloat() + 100f)
//            close()
//        }
//
//        // Light colored path
//        val lightPoint1 = Offset(0f, height * 0.35f)
//        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
//        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
//        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
//        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)
//
//        val lightColoredPath = Path().apply {
//            moveTo(lightPoint1.x, lightPoint1.y)
//            standardQuadFromTo(lightPoint1, lightPoint2)
//            standardQuadFromTo(lightPoint2, lightPoint3)
//            standardQuadFromTo(lightPoint3, lightPoint4)
//            standardQuadFromTo(lightPoint4, lightPoint5)
//            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
//            lineTo(-100f, height.toFloat() + 100f)
//            close()
//        }
//        Canvas(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            drawPath(
//                path = mediumColoredPath,
//                color = feature.mediumColor
//            )
//            drawPath(
//                path = lightColoredPath,
//                color = feature.lightColor
//            )
//        }
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(15.dp)
//        ) {
//            Text(
//                text = feature.title,
//                style = MaterialTheme.typography.h2,
//                lineHeight = 26.sp,
//                modifier = Modifier.align(Alignment.TopStart)
//            )
//            Icon(
//                painter = painterResource(id = feature.iconId),
//                contentDescription = feature.title,
//                tint = Color.White,
//                modifier = Modifier.align(Alignment.BottomStart)
//            )
//            Text(
//                text = "Start",
//                color = TextWhite,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .clickable {
//                        // Handle the click
//                    }
//                    .align(Alignment.BottomEnd)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(ButtonBlue)
//                    .padding(vertical = 6.dp, horizontal = 15.dp)
//            )
//        }
//    }
//}