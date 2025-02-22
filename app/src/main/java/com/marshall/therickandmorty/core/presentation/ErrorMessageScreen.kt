package com.marshall.therickandmorty.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorMessageScreen(modifier: Modifier = Modifier ,message: String, action: () -> Unit) {

    Box (
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = action) {
                Text(text = "Retry")
            }
        }


    }
}


@Composable
@Preview(showBackground = true, name = "Error Message Screen")
fun ErrorMessageScreenPreview() {

    ErrorMessageScreen(message = "Error Message", action = {})

}