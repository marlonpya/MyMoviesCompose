package com.example.marvelcompose.ui.screens.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.repositories.CharactersRepository

@Composable
fun CharacterScreen() {

    var charactersState by rememberSaveable {
        mutableStateOf(emptyList<Character>())
    }

    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.getCharacters()
    }
    CharactersScreen(charactersState)
}

@Composable
fun CharactersScreen(characters: List<Character>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(characters) {
            CharacterItem(character = it)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Column(modifier = Modifier.padding(8.dp)) {
        Card {
            Image(
                painter = rememberImagePainter(character.thumbnail),
                contentDescription = character.name,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                modifier = Modifier.padding(8.dp, 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun CharactersScreenPreview() {
    val characters = (1..10).map {
        Character(
            it,
            "Name $it",
            "Description",
            "https://via.placeholder.com/150x225/FFFF00/000000?text=name$it"
        )
    }
    CharactersScreen(characters)
}