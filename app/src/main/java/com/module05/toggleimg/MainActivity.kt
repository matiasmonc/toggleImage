package com.module05.toggleimg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.module05.toggleimg.ui.theme.ToggleImgTheme
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToggleImgTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Content(paddingValues: PaddingValues) {

    var toggleImage by remember { mutableStateOf(false) }

    val images = listOf(
        R.drawable.example,
        R.drawable.example2,
        R.drawable.example3,
        R.drawable.example4,
        R.drawable.example5
    )

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Title(texto = "!Bienvenido¡")

        Gap()

        Name(texto = "Matías Moncada")

        Gap()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = { toggleImage = !toggleImage }) {
                Text(text = if (!toggleImage) "Mostrar Imagen" else "Ocultar Imagen")
            }
        }

        Gap()

        if(toggleImage){
            ShowImage(images)
        }

    }
}

@Composable
fun Title(texto: String){
    Text(
        text = texto,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 50.sp
    )
}

@Composable
fun Name(texto: String){
    Text(
        text = texto,
        fontSize = 30.sp
    )
}

@Composable
fun Gap(){
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ShowImage(images: List<Int>){

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(images) { item ->
            ImageItem(image = item)
        }
    }
}

@Composable
fun ImageItem(image: Int){
    Image(
        painter = rememberAsyncImagePainter(model = image),
        contentDescription = "Imagen de ejemplo",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
}