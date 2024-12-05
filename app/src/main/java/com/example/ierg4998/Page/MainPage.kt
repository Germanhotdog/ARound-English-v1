package com.example.ierg4998.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ierg4998.ui.theme.HomeCardLayout
import com.example.ierg4998.R
import com.example.ierg4998.data.Datasource
import com.example.ierg4998.model.HomeUI


@Composable
fun MainPage(navController: NavHostController) {

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ,
        ) {
        //Title
        Text(
            text = LocalContext.current.getString(R.string.app_title),
            style = TextStyle(
                fontSize = 48.sp,
                letterSpacing = (-0.01).sp,
                color = Color.Black,

                ),
            modifier = Modifier
                .fillMaxWidth()
            ,
            textAlign = TextAlign.Center
        )
        //App logo
        Image(
            painter = painterResource(R.drawable.app_icon),
            contentDescription ="app icon",
            modifier = Modifier.padding(30.dp)
        )
        //Main Function
        HomeUIApp(navController)

    }// end of column


}


@Composable
fun HomeUIApp(navController: NavHostController) {
    HomeUIList(
        homeUI = Datasource().loadAffirmations(),
        navController = navController
    )

}

@Composable
fun HomeUIList(homeUI: List<HomeUI>, modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    LazyRow(modifier = modifier) {
        itemsIndexed(homeUI){
                index, homeUI ->
            HomeCardLayout(
                homeUI = homeUI,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        //Toast.makeText(context,"Clink $index", Toast.LENGTH_SHORT).show()

                        if(index == 0){
                            navController.navigate("AR")
                            println("click $index")
                        }else if(index == 1){
                            navController.navigate("OCRCamera")
                            println("click $index")
                        }
                        else if (index ==2){
                            navController.navigate("NotePage")
                            println("click $index")
                        }
                    })
        }

    }// end of lazyRow
}


