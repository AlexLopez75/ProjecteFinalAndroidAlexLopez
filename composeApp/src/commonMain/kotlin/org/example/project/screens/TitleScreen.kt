package org.example.project.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.viewmodel.TitleScreenViewModel

@Composable
fun TitleScreen(navigateTo2: () -> Unit, navigateTo3: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Title", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Button(onClick = navigateTo2) { Text("Options") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = navigateTo3) { Text("Play") }

        val vm: TitleScreenViewModel = viewModel { TitleScreenViewModel() }
        Button(onClick = { vm.modifyShowMessage() }) {
            Text("Show message!")
        }
        AnimatedVisibility(vm.showMessage) {
            Text("Hello World!", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}