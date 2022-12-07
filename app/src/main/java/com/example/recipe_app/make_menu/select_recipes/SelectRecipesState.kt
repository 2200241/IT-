package com.example.recipe_app.make_menu.select_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.recipe_app.make_menu.select_conditions.ConditionTab
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeThumb
import com.github.michaelbull.result.mapBoth

class SelectRecipesState(
    private val viewModel: SelectRecipesViewModel,
) {
    val uiState: SelectRecipesUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favoriteRecipeIds: List<String>
        @Composable get() = viewModel.favoriteRecipeIds.collectAsState().value.mapBoth(
            success = { favorites ->  favorites.recipes.map { it.id } },
            failure = { emptyList() }
        )

    val selectedRecipes: List<RecipeThumb>
        @Composable get() = viewModel.selectedRecipes.collectAsState().value.mapBoth(
            success = { it },
            failure = { emptyList() }
        )

    fun onTabClicked(selectedTab: CategoryTab) = viewModel.onTabClicked(selectedTab)

    fun selectRecipe(recipe: RecipeThumb) = viewModel.selectRecipe(recipe)

    fun removeRecipe(id: String) = viewModel.removeRecipe(id)

    fun addMenu() = viewModel.addMenu()

    fun addFavorite(recipe:Recipe) = viewModel.addFavorite(recipe)

    fun removeFavorite(id: String) = viewModel.removeFavorite(id)

}

@Composable
fun rememberSelectRecipesState(
    viewModel: SelectRecipesViewModel = hiltViewModel()
/*
    conditions: String?,
    viewModel: SelectRecipesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = SelectRecipesViewModel.Factory(conditions)
    )
*/
): SelectRecipesState = remember {
    SelectRecipesState(viewModel)
}