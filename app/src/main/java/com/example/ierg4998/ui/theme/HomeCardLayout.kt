package com.example.ierg4998.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ierg4998.model.HomeUI

@Composable
fun HomeCardLayout(homeUI: HomeUI, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(homeUI.imageResourceId),
                contentDescription = stringResource(homeUI.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp).padding(20.dp),
                    contentScale = ContentScale.Crop,

                )

            Text(
                text = LocalContext.current.getString(homeUI.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge,

                )

        }
    }
}
