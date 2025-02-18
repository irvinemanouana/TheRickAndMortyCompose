package com.marshall.therickandmorty.character.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.marshall.therickandmorty.R
import com.marshall.therickandmorty.character.domain.Character
import com.marshall.therickandmorty.ui.theme.aliveStatusColor
import com.marshall.therickandmorty.ui.theme.deadStatusColor
import simpleCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(character: Character) {
    val scrollState = rememberScrollState()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = character.name)
                }
            )
        },
        content = {
            innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )

                Spacer(modifier = Modifier.height(16.dp))


                Row {
                    Surface(
                        modifier = Modifier
                            .size(20.dp)
                            .padding(5.dp)
                            .clip(CircleShape),
                        content = {},
                        color = if (character.isAlive()) aliveStatusColor else deadStatusColor
                    )
                    Text(
                        text = "${character.species} - ${character.status}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }


                Spacer(
                    modifier = Modifier.height(5.dp)
                )
                Text(
                    text = stringResource(R.string.last_known_location_label),
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = character.origin,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(
                    modifier = Modifier.height(5.dp)
                )
                Text(
                    text = stringResource(R.string.first_seen_in_label),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = character.location,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    )
}

@Composable
@Preview
fun CharacterDetailsScreenPreview() {
    CharacterDetailsScreen(character = simpleCharacter)
}