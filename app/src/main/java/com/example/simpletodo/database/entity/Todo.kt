package com.example.simpletodo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "TODO") /*has table's name = TODO*/
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title: String, /* column's name = variable*/
    val createdAt: Date
)

//fun getFakeToDoList() : List<Todo>{
//    return listOf(
//        Todo(1, "Todo 1", Date()),
//        Todo(2, "Todo 1", Date()),
//        Todo(3, "Todo 1", Date()),
//        Todo(4, "Todo 1", Date()),
//        Todo(5, "Todo 1", Date()),
//    )
//}



