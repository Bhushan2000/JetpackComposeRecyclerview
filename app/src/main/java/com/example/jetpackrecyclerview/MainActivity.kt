package com.example.jetpackrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackrecyclerview.compose.TvShowListItem
import com.example.jetpackrecyclerview.data.TvShowList
import com.example.jetpackrecyclerview.model.TvShow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ScrollableColumnDemo()
//            LazyColumnDemo()
//            LazyColumnDemo2 {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//
//            }
            DisplayTvShows {
              //   Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
                startActivity(InfoActivity.intent(this,it))
            }

        }
    }
}

@Composable

fun ScrollableColumnDemo() {

    // THis is like listView in android
    // It compose all the items as once so it may hampered the app performance


    // to adding scroll effect we need to add below
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in 1..100) {
            Text(
                text = "User name $i",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(8.dp)
            )

            Divider(color = Color.Black, thickness = 4.dp)
        }
    }
}

@Composable
fun LazyColumnDemo() {
    // it is Equivalent to recyclerview in Jetpack Compose

    val scrollState = rememberScrollState()

    LazyColumn() {
        items(100) {
            Text(
                text = "User name $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(8.dp)
            )

            Divider(color = Color.Black, thickness = 4.dp)
        }
    }
}


// itemClickListener
// 1) Add lambda expression as a parameter
// 2) Use clickable modifier to pass selected item

// We will add a lambda expression as a parameter to the composable.
//
//selectedItem: (String) -> (Unit)
//
//Input data type of it must be the data type of selected item.
//
//In our case we will select an string. So the input data type should be string.
//
//If we are selecting an User object, then the input data type of the lambda expression would be User.
//
//After that, we would pass the selected list item’s value to the anonymous function defined by the lambda expression using clickable modifier.
//
//.clickable { selectedItem("$it Selected") }


@Composable
fun LazyColumnDemo2(selectedItem: (String) -> (Unit)) {
    // it is Equivalent to recyclerview in Jetpack Compose

    val scrollState = rememberScrollState()

    LazyColumn() {
        items(100) {
            Text(text = "User name $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedItem("$it Selected") }

            )

            Divider(color = Color.Black, thickness = 4.dp)
        }
    }
}


//  Displaying tvShow List

@Composable
fun DisplayTvShows(selectedItem: (TvShow) -> Unit) {
    // In jetpack compose we have a composable named remember.
    //
    //Composable functions can store a single object in memory by using the remember composable.
    //
    //A value computed by remember is stored during the initial composition, and returned during recomposition.
    //
    //So, following best practices , I have used remember composable in this code.

    val tvShow = remember { TvShowList.tvShows }

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            items = tvShow,
            itemContent = {
                TvShowListItem(tvShow = it, selectedItem)
            }
        )
    }
}


