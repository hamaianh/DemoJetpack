package com.example.demojetpack

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demojetpack.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //jetback
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Convesation(messages = dummyData())
                }
            }
        }
    }
}

data class Message(var name: String, var message: String)

@Composable
fun Convesation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "image description",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
        )

        Spacer(modifier = Modifier.width(8.dp))

        //We keep track if the message is expanded or not in this variable
        var isExpanded by remember {
            mutableStateOf(false)
        }
        //subfaceColor will be updated gradually from one color to the other
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        //We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = "Hello ${message.name}",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp, color = surfaceColor) {
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(4.dp),
                    //If the message is expanded, we display all ??t content
                    //otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }

        }
    }
}

@Preview(showBackground = true, name = "Light Mode", showSystemUi = true)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        Convesation(messages = dummyData())
    }
}

fun dummyData(): List<Message> {
    val list = mutableListOf<Message>()
    list.add(Message("Mai Anh Ha", "Hello, B???n kh???e kh??ng"))
    list.add(Message("L?????m", "Thanks, m??nh ???n, c??n b???n sao?"))
    list.add(Message("Mai Anh Ha", "Hello, B???n kh???e kh??ng"))
    list.add(Message("L?????m", "Oh, v???n kh???e b???n hi???n"))
    list.add(
        Message(
            "Mai Anh Ha",
            "T???i nay ??i ???? banh s???n v??? l??m v??i lon lai rai t??m s??? kh??ng b???n?"
        )
    )
    list.add(Message("L?????m", "OK! s???n s??ng"))
    return list
}