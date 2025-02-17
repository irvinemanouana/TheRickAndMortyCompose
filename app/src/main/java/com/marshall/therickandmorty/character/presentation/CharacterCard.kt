package com.marshall.therickandmorty.character.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.marshall.therickandmorty.character.data.entities.Character
import com.marshall.therickandmorty.character.data.entities.CharacterLocation
import com.marshall.therickandmorty.character.data.entities.Origin
import com.marshall.therickandmorty.ui.theme.aliveStatusColor
import com.marshall.therickandmorty.ui.theme.deadStatusColor


@Composable
fun CharacterCard(character: Character) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp)
    ) {
        Row {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(150.dp)

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
                    Surface (
                        modifier = Modifier
                            .size(20.dp)
                            .padding(5.dp)
                            .clip(CircleShape),
                        content = {},
                        color = if (character.status == "Alive") aliveStatusColor else deadStatusColor
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
                    text = character.location.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(
                    modifier = Modifier.height(5.dp)
                )
                Text(
                    text = stringResource(R.string.first_seen_in_label),
                    style = MaterialTheme.typography.titleSmall
                )

            }
        }

    }
}

@Composable
@Preview(showBackground = true, name = "Character Card")
fun CharacterCardPreview() {

    val origin = Origin(
        name = "Earth (C-137)",
        url = "https://rickandmortyapi.com/api/location/1"
    )

    val characterLocation = CharacterLocation(
        name = "Citadel of Ricks",
        url = "https://rickandmortyapi.com/api/location/3"
    )

    // Create the Character instance using the previously created Origin and CharacterLocation
    val character = Character(
        created = "2017-11-04T18:48:46.250Z",
        episode = emptyList(), // Use emptyList() for an empty list
        gender = "Male",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = characterLocation,
        name = "Rick Sanchez",
        origin = origin,
        species = "Human",
        status = "Alive",
        type = "", // Empty string for type
        url = "https://rickandmortyapi.com/api/character/1")


    CharacterCard(character = character)
}