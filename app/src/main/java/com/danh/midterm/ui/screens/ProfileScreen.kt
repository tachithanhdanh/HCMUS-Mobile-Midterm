package com.danh.midterm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.ui.components.ProfileItem
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = viewModel(),
) {
    val profile = profileViewModel.profile
    Column(
        modifier = Modifier.background(color = Color.White).padding(horizontal = 30.dp, vertical = 48.dp).fillMaxHeight(),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = "Profile",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = TextColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        ProfileItem(
            icon = R.drawable.ic_profile,
            label = "Full name",
            initialValue = profile.name,
            onValueChange = { profile.name = it }
        )
        ProfileItem(
            icon = R.drawable.ic_phone,
            label = "Phone number",
            initialValue = profile.phoneNumber,
            onValueChange = { profile.phoneNumber = it }
        )
        ProfileItem(
            icon = R.drawable.ic_message,
            label = "Email",
            initialValue = profile.email,
            onValueChange = { profile.email = it }
        )
        ProfileItem(
            icon = R.drawable.ic_location,
            label = "Address",
            initialValue = profile.address,
            onValueChange = { profile.address = it }
        )
    }

}



@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navHostController = rememberNavController()
    ProfileScreen(navHostController)
}