package org.sopt.play.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LikePreferences(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val LIKED_PRODUCTS = stringSetPreferencesKey("liked_products")
    }

    val likedProductsFlow: Flow<Set<Int>> = dataStore.data.map { preferences ->
        preferences[LIKED_PRODUCTS]?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    }

    suspend fun toggleLike(productId: Int) {
        dataStore.edit { preferences ->
            val currentLikes = preferences[LIKED_PRODUCTS] ?: emptySet()
            val productIdStr = productId.toString()
            val newLikes = if (currentLikes.contains(productIdStr)) {
                currentLikes - productIdStr
            } else {
                currentLikes + productIdStr
            }
            preferences[LIKED_PRODUCTS] = newLikes
        }
    }
}
