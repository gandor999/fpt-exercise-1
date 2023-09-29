// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun app() {
    val (inputString, setInputString) = remember { mutableStateOf("") }
    val (textHeader, setTextHeader) = remember { mutableStateOf("") }
    val listOfInputNumbers: MutableList<Int> = remember {
        mutableStateListOf()
    }

    MaterialTheme {
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Card(
                modifier = Modifier.padding(16.dp),
                elevation = 6.dp,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.End
                ) {
                    Box(
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Card(
                            elevation = 6.dp,
                            backgroundColor = Color(red = 153, green = 221, blue = 200),
                            modifier = Modifier.size(width = 350.dp, height = 50.dp)
                        ) {
                            Text(
                                text = if (textHeader == "") "" else "The selected number is $textHeader",
                                modifier = Modifier.padding(16.dp),
                                textAlign = TextAlign.End,
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp).verticalScroll(
                            rememberScrollState()
                        ),
                        horizontalAlignment = Alignment.End
                    ) {
                        displaySelectedInputs(listOfInputNumbers, setTextHeader)
                    }

                    Box {
                        Row(
                            verticalAlignment = Alignment.Bottom, //horizontalArrangement = Arrangement.End,
                        ) {
                            OutlinedTextField(modifier = Modifier.padding(end = 10.dp),
                                value = inputString,
                                onValueChange = {

                                    setInputString(it)

                                })

                            Button(modifier = Modifier.size(size = 27.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                        red = 149, green = 191, blue = 116
                                    )
                                ),
                                contentPadding = PaddingValues(0.dp),
                                onClick = {
                                    try {
                                        listOfInputNumbers.add(inputString.toInt())
                                    } catch (e: NumberFormatException) {
                                        println("Di pede")
                                    }
                                }) {
                                Text("+")
                            }
                        }
                    }

                }


            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}
