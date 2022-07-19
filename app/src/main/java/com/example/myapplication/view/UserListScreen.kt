package com.example.myapplication.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.User
import com.example.myapplication.theme.green
import com.example.myapplication.theme.typography
import com.example.myapplication.viewmodel.UserViewModel

@Composable
fun UserListScreen(
    navigateToViewScreen: (userId: Int) -> Unit,
    userViewModel: UserViewModel
) {
    MyApp(
        userList = userViewModel.userLiveData,
        navigateToUser = navigateToViewScreen
    )
    userViewModel.getUser()
}

@Composable
fun MyApp(userList: ArrayList<User>, navigateToUser: (userId: Int) -> Unit) {
    Log.e("size", userList.toString())
    Scaffold(
        content = {
            UserListContent(userList, navigateToUser)
        }
    )
}

@Composable
fun UserListContent(userList: ArrayList<User>, navigateToUser: (userId: Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    )
    {
        itemsIndexed(items = userList) { index, item ->
            UserListItem(userList = item, navigateToUser)
        }
    }
}

@Composable
fun UserListItem(userList: User, navigateToUser: (userId: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 10.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        backgroundColor = green,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
    ) {
        Row(Modifier.clickable { navigateToUser(userList.id) }) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = userList.name,
                    style = typography.h6,
                    fontFamily = FontFamily.SansSerif)
                Text(
                    text = userList.email,
                    style = typography.caption,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}