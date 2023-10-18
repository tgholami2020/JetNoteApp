package com.example.jetnotes.data

import com.example.jetnotes.model.Note

class NodesDataSource{
    fun loadNodes(): List<Note>{
        return listOf(
                Note(title= "my 1 node", description = "my 1 description"),
                Note(title= "my 2 node", description = "my 2 description"),
                Note(title= "my 3 node", description = "my 3 description"),
                Note(title= "my 4 node", description = "my 4 description"),
                Note(title= "my 5 node", description = "my 5 description"),
                Note(title= "my 6 node", description = "my 6 description"),
                Note(title= "my 7 node", description = "my 7 description"),
                Note(title= "my 8 node", description = "my 8 description"),
                Note(title= "my 9 node", description = "my 9 description"),
                Note(title= "my 10 node", description = "my 10 description"),
        )
    }
}