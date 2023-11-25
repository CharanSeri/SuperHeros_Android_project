package com.example.superheros

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheros.model.Hero
import com.example.superheros.model.HeroesRepository
import com.example.superheros.ui.theme.Shapes
import com.example.superheros.ui.theme.SuperHerosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHerosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayListOfheroCards()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayListOfheroCards() {

    var heros = HeroesRepository.heroes

    Scaffold(
        topBar = { DisplayHeroCardsTopBar(modifier = Modifier) }
    ) {
        Column {
            Spacer(
                Modifier.height(
                    65.dp
                )
            )
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(heros) {
                    HeroCard(
                        heroOne = it,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayHeroCardsTopBar(modifier: Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Superheros", style = MaterialTheme.typography.displayLarge)
            }
        },
        modifier = modifier
    )
}


@Composable
fun HeroCard(heroOne: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .clip(
                Shapes.medium
            )
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.height(72.dp)) {
                Column(modifier = Modifier.weight(3f)) {
                    Text(
                        text = stringResource(id = heroOne.nameRes),
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = stringResource(id = heroOne.descriptionRes),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.width(16.dp))
                }
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(
                            Shapes.small
                        )
                ) {
                    Image(
                        painter = painterResource(id = heroOne.imageRes),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier
                            .clip(
                                Shapes.small
                            )
                            .height(72.dp)
                    )
                }
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SuperHerosTheme {
        DisplayListOfheroCards()
    }
}