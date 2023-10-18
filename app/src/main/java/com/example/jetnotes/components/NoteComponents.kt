package com.example.jetnotes.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier=Modifier,
    text : String,
    label : String,
    maxLine : Int = 1,
    onTextChange : (String) -> Unit,
    onImeAction: ()-> Unit = {}
){
    val keyboardController= LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier,
        TextFieldvalue = text,
        onValueChange = onTextChange,
        maxLine= maxLine,
        keyboardOptions= KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        label = Text(text = label),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        )
}

fun TextField(modifier: Modifier, TextFieldvalue: String, onValueChange: (String) -> Unit, maxLine: Int, keyboardOptions: KeyboardOptions, label: Unit, keyboardActions: KeyboardActions) {

}

@Composable
fun NoteButton(
    modifier: Modifier= Modifier,
    text: String,
    onclick: () -> Unit,
    enabled: Boolean= true,
) {
    Button(onClick = onclick,
    shape= CircleShape,
        enabled = enabled,
        modifier = modifier
        ){
        //content of my button
        Text(text)
    }
}


