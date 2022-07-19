package com.example.myapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.User
import com.example.myapplication.theme.green
import com.example.myapplication.theme.typography
import com.example.myapplication.theme.white
import com.example.myapplication.viewmodel.UserViewModel

@Composable
fun UserDetailsView(
    userViewModel: UserViewModel,
    selectedUser: User
) {
    Scaffold {
        UserListItem(userList = selectedUser)
    }
}

@Composable
fun UserListItem(userList: User) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 10.dp
            )
            .fillMaxWidth(),
        elevation = 5.dp,
        backgroundColor = white,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = userList.name,
                    color = green,
                    style = typography.h6,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = userList.email,
                    color = green,
                    style = typography.caption,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = userList.gender,
                    color = green,
                    style = typography.caption,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = userList.status,
                    color = green,
                    style = typography.caption,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}