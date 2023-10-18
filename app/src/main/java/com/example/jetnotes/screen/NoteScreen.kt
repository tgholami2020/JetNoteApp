package com.example.jetnotes.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnotes.R
import com.example.jetnotes.components.NoteButton
import com.example.jetnotes.data.NodesDataSource
import com.example.jetnotes.model.Note
import com.example.jetnotes.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    nodes: List<Note>,
    onAddNode: (Note) -> Unit,
    onRemoveNode: (Note) -> Unit
){
    var title by remember{
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    val scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior()
Column (modifier= Modifier
    .padding(6.dp)
    .nestedScroll(scrollBehavior.nestedScrollConnection)){
    TopAppBar(title = { 
                      Text(text = stringResource(id = R.string.app_name))
    },
        actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription ="Icon" )
        },
          scrollBehavior = scrollBehavior
        )
    //Content
    Column(modifier=Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                modifier= Modifier.padding(top= 8.dp, bottom = 8.dp),
                value = title,
                label = {
                    Text(text = "Type Your Name Here")
                },
                enabled = true,
                maxLines = 1,
                onValueChange = {
                if (it.all {char->
                        char.isLetter() || char.isWhitespace()
                    }) title= it
            },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions (
                    onNext = {
                        println("Hello world")
                    }
                ))
            TextField(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                value = description,
                label = {
                        Text(text = "Type Your Password Here")
                },
                enabled = true,
                maxLines = 1,
                onValueChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        println("Hello world")
                    }
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
              //  NoteInputText(text = title, label = "Hello", onTextChange = {})
//        NoteInputText(
//            modifier= Modifier.padding(top= 8.dp, bottom = 8.dp),
//            text = title,
//            label = "Title",
//            onTextChange = {
//                if (it.all {char->
//                        char.isLetter() || char.isWhitespace()
//                    }) title= it
//
//            })
//        NoteInputText(
//            modifier= Modifier.padding(top= 8.dp, bottom = 8.dp),
//            text = description,
//            label = "Add a note",
//            onTextChange = {
//                if (it.all {char->
//                        char.isLetter() || char.isWhitespace()
//                    }) description= it
//            })
        //my Button
        NoteButton(text = "Save",
            onclick = {
                if (title.isNotEmpty() && description.isNotEmpty()){
                    //save,add to the list
                    onAddNode(Note(
                        title= title,
                        description= description))
                    title = ""
                    description= ""
                    Toast.makeText(context,"Node added",Toast.LENGTH_SHORT).show()
                }
        })
    }
    Divider(modifier= Modifier.padding(10.dp))
    LazyColumn{
        items(nodes){node->
            NodeRow(node = node, onNodeClicked ={
                onRemoveNode(node)
            })
        }
    }

}
}

@Composable
fun NodeRow(
    modifier: Modifier= Modifier,
    node: Note,
    onNodeClicked: (Note) -> Unit){
    Surface (modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 28.dp, bottomStart = 28.dp))
        .fillMaxWidth(),
        color = Color(0xFFCCD2D6),
        shadowElevation = 6.dp){
        Column (
            modifier
                .clickable {
                    onNodeClicked(node)
                }
                .padding(horizontal = 14.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = node.title,
                style = MaterialTheme.typography.labelMedium)
            Text(text = node.description,
                style = MaterialTheme.typography.labelMedium)
            Text(text = formatDate(node.entryDate.time),
                style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
NoteScreen(
    nodes = NodesDataSource().loadNodes(), onAddNode = {}, onRemoveNode = {}
)
}