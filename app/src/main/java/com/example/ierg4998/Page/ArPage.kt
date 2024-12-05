package com.example.ierg4998

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.content.Context
import com.example.ierg4998.ArFunction.ArFunction
import com.example.ierg4998.ArFunction.ArFunction2
import com.example.ierg4998.ArFunction.ArFunction3

@Composable
fun ArPage(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("GamePrefs", Context.MODE_PRIVATE)
    val trophy1 = sharedPreferences.getInt("trophy1", 0)
    val trophy2 = sharedPreferences.getInt("trophy2", 0)
    val trophy3 = sharedPreferences.getInt("trophy3", 0)
    val totalTrophy = trophy1 + trophy2 + trophy3


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "AR Page", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(65.dp))
        Text(text = "Trophy : $totalTrophy / 3", fontSize = 20.sp)
        Button(onClick = {
            val intent = Intent(context, ArFunction::class.java)
            context.startActivity(intent)
        }) {
            Text(text = if (trophy1 == 1) "Level 1 (DONE)" else "Level 1", fontSize = 20.sp)
        }

        Button(onClick = {
            val intent = Intent(context, ArFunction2::class.java)
            context.startActivity(intent)
        }) {
            Text(text = if (trophy2 == 1) "Level 2 (DONE)" else "Level 2", fontSize = 20.sp)
        }
        Button(onClick = {
            val intent = Intent(context, ArFunction3::class.java)
            context.startActivity(intent)
        }) {
            Text(text = if (trophy3 == 1) "Level 3 (DONE)" else "Level 3", fontSize = 20.sp)
        }



        Button(onClick ={
            val editor = sharedPreferences.edit()
            editor.putInt("trophy1", 0)
            editor.putInt("trophy2", 0)
            editor.putInt("trophy3", 0)
            editor.apply()


        }) {
            Text(text = "Refresh trophy", fontSize = 20.sp)
        }

    }


}