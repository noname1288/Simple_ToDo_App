package com.example.simpletodo.ui.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpletodo.database.entity.Todo
import com.example.simpletodo.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(innerPadding: PaddingValues, viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var searchKeyword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OutlinedTextField(
                value = searchKeyword,
                onValueChange = { searchKeyword = it },
                modifier = Modifier
                    .height(50.dp)
                    .width(230.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.addTodo(searchKeyword)
                searchKeyword = ""
            }) {
                Text(text = "Add")
            }
        }

        todoList?.let {
            Log.d("TodoList re-render -->", it.toString())
            LazyColumn {
                itemsIndexed(it) { index, todo ->
                    ItemText(todo, viewModel)
                }
            }
        }?: Text(text="No item here")
    }

}


@Composable
fun ItemText(todo: Todo, viewmodel: TodoViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))

            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(todo.createdAt),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = todo.title, fontSize = 17.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {
            viewmodel.deleteTodo(todo.id)
        }) {
            Icon(
                painter = painterResource(id = com.example.simpletodo.R.drawable.baseline_delete_24),
                contentDescription = "Delete"
            )
        }

    }
}