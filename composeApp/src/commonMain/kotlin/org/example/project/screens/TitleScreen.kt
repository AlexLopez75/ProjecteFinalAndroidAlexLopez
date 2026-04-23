package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import org.jetbrains.compose.resources.painterResource
import kotlinproject.composeapp.generated.resources.*

@Composable
fun TitleScreen(
    navigateTo2: () -> Unit,
    navigateTo3: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (jojoTitle, jojoCustom, btnOption, btnPlay) = createRefs()

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
        )

        Button(
            onClick = navigateTo2,
            modifier = Modifier.constrainAs(btnOption) {
                top.linkTo(jojoCustom.bottom, margin = 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Options")
        }

        Button(
            onClick = navigateTo3,
            modifier = Modifier.constrainAs(btnPlay) {
                top.linkTo(btnOption.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Play")
        }
    }
}