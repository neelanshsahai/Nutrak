package com.example.nutrak.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalDetailsViewModel @Inject constructor(
    dataStore: DataStore<Preferences>
): ViewModel() {

    val personalDetailsState = PersonalDetailsState()

    fun updateGender(code: Int) {
        personalDetailsState.gender = when (code) {
            0 -> Gender.MALE
            1 -> Gender.FEMALE
            else -> Gender.UNSPECIFIED
        }
    }

    fun updateHeight(ft: Int, inch: Int) {
        personalDetailsState.height = Pair(ft, inch)
    }

    fun updateAge(age: Int) {
        personalDetailsState.age = age
    }

    fun updateWeight(weight: Int) {
        personalDetailsState.weight = weight
    }

    fun updateGoal(goal: String) {
        personalDetailsState.goal = goal
    }

    fun updatePreferences(dietary: Int, allergy: Int) {
        personalDetailsState.apply {
            dietaryPref = when (dietary) {
                0 -> DietaryPreference.VEGAN
                1 -> DietaryPreference.KETO
                else -> DietaryPreference.UNSPECIFIED
            }

            allergyPref = when (allergy) {
                0 -> Allergy.GLUTEN_FREE
                1 -> Allergy.NUT_FREE
                else -> Allergy.UNSPECIFIED
            }
        }
    }
}

data class PersonalDetailsState(
    var gender: Gender = Gender.UNSPECIFIED,
    var height: Pair<Int, Int> = Pair(0, 0),
    var age: Int = 0,
    var weight: Int = 0,
    var goal: String = "",
    var dietaryPref: DietaryPreference = DietaryPreference.UNSPECIFIED,
    var allergyPref: Allergy = Allergy.UNSPECIFIED,
)

enum class Gender {
    MALE,
    FEMALE,
    UNSPECIFIED,
}

enum class DietaryPreference {
    VEGAN,
    KETO,
    UNSPECIFIED,
}

enum class Allergy {
    GLUTEN_FREE,
    NUT_FREE,
    UNSPECIFIED,
}