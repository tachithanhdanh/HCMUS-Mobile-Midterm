package com.danh.midterm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.danh.midterm.mock.MockData
import com.danh.midterm.model.ModelConstants
import com.danh.midterm.model.Profile

class ProfileViewModel: ViewModel() {
    // Hold the profile
    var profile by mutableStateOf<Profile>(MockData.profile)
        private set

    // Update the profile
    fun updateProfile(newProfile: Profile) {
        profile = newProfile
    }

    fun getProfileName(): String {
        return profile.name
    }

    fun getProfileAddress(): String {
        return profile.address
    }

    fun getProfilePhone(): String {
        return profile.phoneNumber
    }

    fun getProfileEmail(): String {
        return profile.email
    }

    fun updateProfileName(name: String) {
        profile.name = name
    }

    fun updateProfileAddress(address: String) {
        profile.address = address
    }

    fun updateProfilePhone(phone: String) {
        profile.phoneNumber = phone
    }

    fun updateProfileEmail(email: String) {
        profile.email = email
    }

    fun getProfilePoints(): Int {
        return profile.points
    }

    fun updateProfilePoints(points: Int) {
        profile = profile.copy(points = points)
    }

    fun addPoints(points: Int) {
        profile = profile.copy(points = profile.points + points)
    }

    fun redeemPoints(points: Int) {
        profile = profile.copy(points = profile.points - points)
    }

    fun addStamps(stamps: Int) {
        val newStamps = if (profile.stamps + stamps >= ModelConstants.MAX_STAMPS) {
            ModelConstants.MAX_STAMPS
        } else {
            profile.stamps + stamps
        }
        profile = profile.copy(stamps = newStamps)
    }

    fun setStamps(stamps: Int) {
        profile = profile.copy(stamps = stamps)
    }
}