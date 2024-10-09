package id.ihsan.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.ihsan.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->// untuk memodifikasi tampilan awal
                    ImageSlider(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ImageSlider(name: String, modifier: Modifier = Modifier) {
    // List of image resources
    val images = listOf(// untuk menampung multidata image
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    val texts = listOf(// untuk menampung multidata text
        "Mohammad Salah",
        "Cristiano Ronaldo",
        "Lionel Messi"
    )

    // State to track the current image index
    var currentIndex by remember { mutableStateOf(0) }// untuk menyimpan perubahan index pada image
    var currentTextIndex by remember { mutableStateOf(0) }// untuk menyimpan perubahan index pada text

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display current image
        Image(
            painter = painterResource(id = images[currentIndex]),// untuk menampilkan image sesuai index
            contentDescription = "Image Slider",// jika image gagal ditampilkan maka akan muncul content description
            modifier = Modifier.size(300.dp)
        )

        Text(texts[currentTextIndex])// untuk menampilkan text sesuai index

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons to navigate images
        Row {
            Button(onClick = {// event onclick
                // Logic to show previous text & image
                if (currentIndex > 0) {// decrement index
                    currentIndex--
                    currentTextIndex--
                } else {
                    currentIndex = images.size - 1 // Loop to the last image
                    currentTextIndex = texts.size - 1 // Loop to the last text
                }
            }) {
                Text("Previous")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                // Logic to show next image & text
                if (currentIndex < images.size - 1) {// increment index
                    currentIndex++
                    currentTextIndex++
                } else {
                    currentIndex = 0 // Loop back to the first image
                    currentTextIndex = 0 // Loop back to the first text
                }
            }) {
                Text("Next")
            }
        }
    }
}

@Preview
@Composable
fun PreviewImageSlider() {
    ImageSlider("")
}
