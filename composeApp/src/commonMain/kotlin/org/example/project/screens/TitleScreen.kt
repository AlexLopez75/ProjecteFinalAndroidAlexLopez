package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import org.jetbrains.compose.resources.painterResource
import kotlinproject.composeapp.generated.resources.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.platform.testTag
import org.example.project.audio.AudioPlayer

@Composable
fun TitleScreen(
    navigateTo2: () -> Unit,
    navigateTo3: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .testTag("title_screen_container"),
        ) {
        val (jojoTitle, jojoCustom, jojoStands, btnOption, btnPlay) = createRefs()

        Image(
            painter = painterResource(Res.drawable.jojo_bizarre_adventure),
            contentDescription = "Jojo title",
            modifier = Modifier
                .size(width = 300.dp, height = 200.dp)
                .constrainAs(jojoTitle) {
                    top.linkTo(parent.top, margin = 80.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .testTag("jojo_main_logo")
        )

        Image(
            painter = painterResource(Res.drawable.the_bizarre_memory),
            contentDescription = "Custom jojo title",
            modifier = Modifier
                .size(width = 330.dp, height = 60.dp)
                .constrainAs(jojoCustom) {
                    top.linkTo(jojoTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .testTag("bizarre_memory_logo")
        )

        Image(
            painter = painterResource(Res.drawable.jojo_stands),
            contentDescription = "Jojo Image",
            modifier = Modifier
                .size(300.dp)
                .constrainAs(jojoStands) {
                    top.linkTo(jojoCustom.top, margin = 80.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .testTag("jojo_stands")
        )

        Button(
            onClick = navigateTo2,
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxWidth(0.7f)
                .height(70.dp)
                .constrainAs(btnOption) {
                top.linkTo(jojoStands.bottom, margin = 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                }
                .testTag("btn_custom_game"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = CutCornerShape(12.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center){
                Text(
                    "CUSTOM GAME",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Custom game",
                    tint = Color.Black
                )
            }
        }

        Button(
            onClick = navigateTo3,
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxWidth(0.7f)
                .height(70.dp)
                .constrainAs(btnPlay) {
                top.linkTo(btnOption.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                }
                .testTag("btn_quick_play"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = CutCornerShape(12.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    "QUICK PLAY",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                )


                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Custom game",
                    tint = Color.Black
                )
            }
        }
    }
}