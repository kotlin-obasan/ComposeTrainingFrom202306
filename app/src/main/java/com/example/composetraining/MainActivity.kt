package com.example.composetraining

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import com.example.composetraining.ui.theme.Teal200


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrainingTheme {
                Scaffold(
                    // topBarを指定する
                    topBar = { MyTopAppBar() },
                    // ボトムナビゲーション
                    bottomBar = { MyBottomAppBar() }
                ) { contentPadding ->
                    // Screen content
                    Box(modifier = Modifier.padding(contentPadding)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.dialog_alert_title))
        },
    )
}

@Composable
fun MyBottomAppBar() {
    BottomAppBar(backgroundColor = MaterialTheme.colors.primary) {
        Text(text = "BottomAppBar")
    }
}

@Composable
fun MainScreen() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            // ポイント閲覧するところ
            PointView(51, 5)
            // 広告置き場
            TopAdView()
            // タブ
            TabLayout()
            // コンテンツ部分
            MenuViewPager()
        }

    }
}

@Composable
fun PointView(point: Int, unApprovalPoint: Int) {
    Text(
        text = "$point さん (ポイント:$unApprovalPoint pt)",
        modifier = Modifier
            .background(color = Teal200)
            .fillMaxWidth()
            .height(32.dp)
    )

}

@Composable
fun TopAdView() {
//    ImageView
    Image(
        painterResource(R.drawable.btn_star_big_on),
        contentDescription = "Advertise Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentScale = ContentScale.Fit,
        alignment = Alignment.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTrainingTheme {
        PointView(1, 2)
    }
}

@Composable
fun TabLayout() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Tab(
            selected = selectedTabIndex == 0,
            onClick = {
                selectedTabIndex = 0
            },
            text = {
                Text(
                    text = "タブ１",
                    fontWeight = FontWeight.Bold,
                    color = if (selectedTabIndex == 0) Color.Red else Color.Blue
                )
            }
        )
        Tab(
            selected = selectedTabIndex == 1,
            onClick = {
                selectedTabIndex = 1
            },
            text = {
                Text(
                    text = "タブ２",
                    fontWeight = FontWeight.Bold,
                    color = if (selectedTabIndex == 1) Color.Red else Color.Blue
                )
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuViewPager() {
    HorizontalPager(
        pageCount = 4
    ) { pageIndex ->
        Box(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.LightGray)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$pageIndex", style = MaterialTheme.typography.h1)
        }
    }
}
