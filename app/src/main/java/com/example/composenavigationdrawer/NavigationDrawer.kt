package com.example.composenavigationdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = menuItems,
                onItemClick = {
                    when (it.id) {
                        "home",
                        "settings",
                        "chat" -> println("Clicked on ${it.title}")
                        else -> println("Clicked on ${it.title}")
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
        /** This will only allows to close the drawer by swiping. There seems a bug that
         * we can open the drawer by swiping anywhere in the screen. It should only open the
         * drawer when we start swiping from the edge of the screen. */
    ) {

    }
}

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Header",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    items: List<MenuItem>,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(1.dp)
                    .clickable {
                        onItemClick(item)
                    }
                    .background(Color(0xFFD4F4E4))
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title)
                Spacer(modifier = modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    color = Color.Red,
                    modifier = modifier.weight(1f) //This way the text will take the available width on the row.
                )
            }
        }
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "My very cool app")
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}

val menuItems = listOf(
    MenuItem(
        id = "home",
        title = "Home",
        icon = Icons.Default.Home
    ),
    MenuItem(
        id = "chat",
        title = "Chat",
        icon = Icons.Default.Send
    ),
    MenuItem(
        id = "gallery",
        title = "Gallery",
        icon = Icons.Default.Star
    ),
    MenuItem(
        id = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    ),
)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationDrawer()
}