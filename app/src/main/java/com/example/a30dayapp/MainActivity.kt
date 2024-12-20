package com.example.a30dayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.animateColorAsState
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.res.fontResource
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import com.example.a30dayapp.ui.theme._30DayAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DayAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "30 Day Vietnamese Food", fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                ) { innerPadding ->
                    FoodTipsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class FoodTip(
    val title: Int,
    val titleResId: Int,
    val descriptionResId: Int,
    val imageResId: Int
)

val foodTips = listOf(
    FoodTip(R.string.titleday1 ,R.string.pho ,R.string.food_tip_pho, R.drawable.pho),
    FoodTip(R.string.titleday2 ,R.string.banhmi ,R.string.food_tip_banh_mi, R.drawable.banhmi),
    FoodTip(R.string.titleday3 ,R.string.banhda ,R.string.food_tip_banhda, R.drawable.banhda),
    FoodTip(R.string.titleday4 ,R.string.buncha ,R.string.food_tip_buncha, R.drawable.buncha),
    FoodTip(R.string.titleday5 ,R.string.chaca ,R.string.food_tip_chaca, R.drawable.chaca),
    FoodTip(R.string.titleday6 ,R.string.com ,R.string.food_tip_com, R.drawable.com),
    FoodTip(R.string.titleday7 ,R.string.goi ,R.string.food_tip_goi, R.drawable.goi),
    FoodTip(R.string.titleday8 ,R.string.nemcua ,R.string.food_tip_nemcua, R.drawable.nemcua),
    FoodTip(R.string.titleday9 ,R.string.thittrau ,R.string.food_tip_thittrau, R.drawable.thittrau),
)

val srirachaFontFamily = FontFamily(Font(R.font.sriracha))

@Composable
fun FoodTipsScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(8.dp)
    ) {
        items(foodTips.size) { index ->
            FoodTipCard(foodTip = foodTips[index])
        }
    }
}

@Composable
fun FoodTipCard(foodTip: FoodTip) {
    var expanded by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.White
    )
    val scale by animateFloatAsState(
        targetValue = if (expanded) 1.05f else 1f
    )

    Card(
        modifier = Modifier
            .padding(16.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .background(backgroundColor)
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.height(40.dp),
                text = stringResource(id = foodTip.title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = stringResource(id = foodTip.titleResId),
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = srirachaFontFamily),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = foodTip.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = foodTip.descriptionResId),
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = srirachaFontFamily),
                maxLines = if (expanded) Int.MAX_VALUE else 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (expanded) "" else "...",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable { expanded = !expanded }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodTipsScreenPreview() {
    _30DayAppTheme {
        FoodTipsScreen()
    }
}