package com.appcreate.ligthon.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.appcreate.ligthon.R

@Composable
@Preview
fun BottomAddCardView(onClick: () -> Unit = {}, text: String = "Add new Powertrack to check") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(height = 120.dp)
            .shadow(elevation = 25.dp)
            .zIndex(1f)
            .background(color = Color(0xfffffcfc)),
    ) {
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(11.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfffcea8d)),
            border = BorderStroke(1.dp, Color(0xfff6980b).copy(alpha = 0.45f)),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 50.dp, end = 50.dp),
            content = {
                Text(
                    text = text,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.inder)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        )
    }
}

@Composable
@Preview
fun AddNewNetworkDialog(
    onDialogDismiss: (Boolean) -> Unit = {},
    onAddButtonClicked: (Pair<String, String>) -> Unit = {}
) {

    val nameField = remember { mutableStateOf("") }
    val ipField = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDialogDismiss(false) }) {
        Surface(
            shape = RoundedCornerShape(25.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(14.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add new Network",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.inder)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = "",
                        tint = colorResource(android.R.color.darker_gray),
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clickable { onDialogDismiss(false) }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(textFieldValueState = nameField, placeholderText = "Enter Network Name")

                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    patternRegex = "^((\\d+\\.+\\d+)*|(\\d+\\.)*|(\\d+)*)$",
                    textFieldValueState = ipField,
                    placeholderText = "Enter Ip Address")

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(
                    onClick = {
                        onAddButtonClicked(Pair(nameField.value, ipField.value))
                        onDialogDismiss(false)
                    },
                    enabled = nameField.value.isNotEmpty() && ipField.value.isNotEmpty(),
                    shape = RoundedCornerShape(11.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xfffcea8d),
                        disabledBackgroundColor = Color.LightGray
                    ),
                    border = BorderStroke(1.dp, Color(0xfff6980b).copy(alpha = 0.45f)),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    content = {
                        Text(
                            text = "Add new Power track",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.inder)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun CustomTextField(
    patternRegex: String = "[a-zA-z\\s]*",
    textFieldValueState: MutableState<String> = mutableStateOf(""),
    placeholderText: String ="Enter value",
    placeholderTextColor: Color = Color.DarkGray,
) {
    val pattern = Regex(patternRegex)
    val placeholderTextState = remember {
        mutableStateOf(placeholderText)
    }
    val placeholderTextColorState = remember {
        mutableStateOf(placeholderTextColor)
    }
    TextField(
        value = textFieldValueState.value,
        placeholder = {
            Text(
                text = placeholderTextState.value,
                color = placeholderTextColorState.value
            )
        },
        onValueChange = {
            if (it.matches(pattern)) {
                textFieldValueState.value = it.take(18)
            }
            if (it.isEmpty()) {
                placeholderTextState.value = "Fill the field!"
                placeholderTextColorState.value = Color.Red
            } else {
                placeholderTextState.value = placeholderText
                placeholderTextColorState.value = Color.DarkGray
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color(0xffd9d9d9).copy(alpha = 0.5f),
            unfocusedIndicatorColor = Color(0xffd9d9d9).copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(height = 59.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    )
}

@Composable
@Preview
fun NetworkCardView(
    cardName: String = "My WiFi",
    isLightOn: Boolean = true,
    onDeleteFromList: () -> Unit = {}
) {
    Card(
        backgroundColor = Color(0xfffffcfc),
        contentColor = Color.Black,
        shape = RoundedCornerShape(size = 20.dp),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x59000000),
                ambientColor = Color(0x59000000)
            )
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier.padding(top = 25.dp)) {
                Text(
                    text = cardName,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.inder)),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(id = if (isLightOn) R.drawable.bulb_on else R.drawable.bulb_off),
                    contentDescription = "bulb_off 2",
                    modifier = Modifier
                        .size(width = 33.dp, height = 61.dp)
                )
            }

            Icon(
                imageVector = Icons.Filled.Cancel,
                contentDescription = "",
                tint = colorResource(android.R.color.darker_gray),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(30.dp)
                    .height(30.dp)
                    .clickable { onDeleteFromList() },
            )
        }
    }
}
