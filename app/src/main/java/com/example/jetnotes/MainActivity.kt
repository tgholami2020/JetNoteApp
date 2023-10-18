package com.example.jetnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetnotes.screen.NoteScreen
import com.example.jetnotes.screen.NoteViewModel
import com.example.jetnotes.ui.theme.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels ()
                   NotesApp(noteViewModel = noteViewModel )
                }
            }
        }
    }
}
@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val notesList = noteViewModel.noteList.collectAsState().value
    NoteScreen(
        nodes = notesList,
        onAddNode = {
            noteViewModel.addNote(it)
        },
        onRemoveNode = {
           noteViewModel.removeNote(it)
        }
    )
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNotesTheme {
    NoteScreen(
        nodes = emptyList(), onAddNode = {}, onRemoveNode = {}
    )
    }
}