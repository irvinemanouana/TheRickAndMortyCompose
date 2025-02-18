import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.marshall.therickandmorty.character.domain.Character

/**
 * A custom [Modifier] that creates a parallax scrolling effect for a composable.
 *
 * This modifier adjusts the vertical position of the composable based on the current scroll position
 * of a [ScrollState], creating a parallax effect. The intensity of the effect is controlled by the
 * [rate] parameter.
 *
 * @param scrollState The [ScrollState] that this modifier will observe to determine the scroll position.
 * @param rate The rate at which the parallax effect should occur.
 *             - A positive value will move the composable down as the user scrolls down.
 *             - A negative value will move the composable up as the user scrolls down.
 *             - A value of 0 will result in no parallax effect.
 *             - The larger the absolute value of [rate], the slower the parallax effect.
 * @return A [Modifier] that applies the parallax effect.
 */
fun Modifier.parallaxLayoutModifier(scrollState: ScrollState, rate: Int) =
    layout { measurable, constraints ->
        // Measure the composable with the given constraints.
        val placeable = measurable.measure(constraints)

        // Calculate the vertical offset based on the scroll position and the rate.
        // If rate is positive, the offset will be positive (moving the composable down).
        // If rate is negative, the offset will be negative (moving the composable up).
        // If rate is 0, the offset will be 0 (no parallax effect).
        val height = if (rate > 0) scrollState.value / rate else scrollState.value

        // Determine the size of the layout.
        // The width is the same as the measured composable's width.
        // The height is the measured composable's height plus the calculated offset.
        layout(placeable.width, placeable.height + height) {
            // Place the composable within the layout.
            // The x-coordinate is 0 (left edge).
            // The y-coordinate is the calculated offset (height).
            placeable.placeRelative(0, height)
        }
    }


val simpleCharacter = Character(
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