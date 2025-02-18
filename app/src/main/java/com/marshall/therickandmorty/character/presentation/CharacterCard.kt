package com.marshall.therickandmorty.character.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.marshall.therickandmorty.R
import com.marshall.therickandmorty.character.domain.Character
import com.marshall.therickandmorty.ui.theme.TheRickAndMortyTheme
import com.marshall.therickandmorty.ui.theme.aliveStatusColor
import com.marshall.therickandmorty.ui.theme.deadStatusColor


@Composable
fun CharacterCard(character: Character, onClick:(Character) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp)
            .clickable {
                onClick(character)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(170.dp)

            )
            Spacer(
                modifier = Modifier.width(10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineSmall
                )
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

    }
}

@Composable
@Preview(showBackground = true, name = "Character Card")
fun CharacterCardPreview() {
    val character = Character(
        created = "2017-11-04T18:48:46.250Z",
        gender = "Male",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = "Citadel of Ricks",
        name = "Rick Sanchez",
        origin = "Earth (C-137)",
        species = "Human",
        status = "Alive",
        type = "", // Empty string for type
        url = "https://rickandmortyapi.com/api/character/1"
    )

    TheRickAndMortyTheme {
        CharacterCard(character = character, onClick = {})
    }

}