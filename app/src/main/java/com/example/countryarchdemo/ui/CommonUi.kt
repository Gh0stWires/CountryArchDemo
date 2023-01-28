package com.example.countryarchdemo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countryarchdemo.R
import com.example.countryarchdemo.ui.theme.CountryArchDemoTheme

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
    )
}

@Composable
fun LabeledText(label: String, text: String) {
    Row() {
        Text(label, style = TextStyle(fontSize = 16.sp))
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text, style = TextStyle(fontSize = 16.sp))
    }
}

@Composable
fun ErrorDialog(message: String, setState: () -> Unit) {
    CountryArchDemoTheme {
        Column {
            val openDialog = remember { mutableStateOf(true) }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(message)
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                setState()
                                openDialog.value = false
                            }
                        ) {
                            Text("Try again")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                setState()
                                openDialog.value = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }
    }
}