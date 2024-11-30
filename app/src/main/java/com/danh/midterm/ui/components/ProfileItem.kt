package com.danh.midterm.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.LightTextColor

@Composable
fun ProfileItem(
    @DrawableRes icon: Int,
    label: String,
    initialValue: String,
    onValueChange: (String) -> Unit
) {
    // Trạng thái để kiểm soát chế độ chỉnh sửa
    var isEditing by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(initialValue) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = CoffeeItemCardColor, shape = CircleShape)
                .border(width = 2.dp, color = Color.Transparent, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Hiển thị nội dung: Text hoặc TextField
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = TextStyle.Default.copy(
                    color = LightTextColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            if (isEditing) {
                // Hiển thị modal (Dialog) để chỉnh sửa thông tin
                AlertDialog(
                    onDismissRequest = { isEditing = false }, // Đóng modal khi click ra ngoài
                    title = { Text(text = "Edit $label") },
                    text = {
                        TextField(
                            value = textValue,
                            onValueChange = { newText ->
                                textValue = newText
                            },
                            label = { Text("Enter new value", color = DarkBlue) },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                color = DarkBlue,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W600
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = DarkBlue,
                                unfocusedIndicatorColor = DarkBlue,
                            )
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                onValueChange(textValue) // Lưu giá trị mới

                                isEditing = false // Đóng modal và trở lại chế độ xem
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = DarkBlue,
                                containerColor = Color.Transparent
                            )
                        ) {
                            Text("Save")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                isEditing = false // Đóng modal mà không lưu
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = DarkBlue,
                                containerColor = Color.Transparent
                            )
                        ) {
                            Text("Cancel")
                        }
                    },
                    containerColor = Color.White,
                    textContentColor = DarkBlue,
                    titleContentColor = DarkBlue,
                    iconContentColor = DarkBlue
                )
            }
            Text(
                text = textValue,
                style = TextStyle.Default.copy(
                    color = DarkBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600
                )
            )
        }

        IconButton(onClick = { isEditing = !isEditing }) {
            Icon(
                painterResource(id = R.drawable.ic_edit),
                contentDescription = if (isEditing) "Save" else "Edit"
            )
        }
    }
}
