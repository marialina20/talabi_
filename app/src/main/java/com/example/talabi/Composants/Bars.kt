package com.example.talabi.Composants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.white

@Composable
fun TopBar(content: String,imageVector:ImageVector,onClick: ()-> Unit,padding:Int=40,contentColor:Color= gray) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(top = padding.dp, bottom = 10.dp)

    ) {
        CirculedOutlineButton(
            onClick = onClick,
            sizeButton = 40,
            sizeIcon = 30,
            imageVector = imageVector,
            containerColor = Color.Unspecified,
            borderStroke = 1f,
            contentColor = contentColor,
            borderColor = contentColor,
        )
        Text(text = content, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = contentColor))
        Spacer(modifier = androidx.compose.ui.Modifier.width(30.dp))
    }
}