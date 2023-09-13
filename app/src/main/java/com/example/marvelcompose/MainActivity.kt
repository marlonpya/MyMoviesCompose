package com.example.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.ui.screens.characters.CharacterScreen
import com.example.marvelcompose.ui.screens.characters.CharactersScreen
import com.example.marvelcompose.ui.theme.MarvelComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAp {

                CharacterScreen()
            }
        }
    }
}

@Composable
fun MarvelAp(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}