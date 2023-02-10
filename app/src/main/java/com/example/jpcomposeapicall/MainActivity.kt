package com.example.jpcomposeapicall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.jpcomposeapicall.ui.theme.JPComposeAPICallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val vm = TodoViewModel()

        super.onCreate(savedInstanceState)
        setContent {
            JPComposeAPICallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoView(vm)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoView(vm: TodoViewModel) {
    LaunchedEffect(Unit, block = {
        vm.getTodoList()
    })
    Scaffold(content = {
        if (vm.errorMessage.isEmpty()) {
            Column(modifier = Modifier.padding(16.dp)) {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(vm.todoList) { todo ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                ){
                                    AsyncImage(model = todo.url, contentDescription = todo.title,modifier = Modifier
                                        .padding(end = 16.dp) )

                                    Text(
                                        todo.title,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                }
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
            }
        }else{
            Text(vm.errorMessage)
        }
    })

}

