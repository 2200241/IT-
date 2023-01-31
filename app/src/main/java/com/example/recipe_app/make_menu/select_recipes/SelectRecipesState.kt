package com.example.recipe_app.make_menu.select_recipes

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeThumb
import com.github.michaelbull.result.mapBoth

class SelectRecipesState(
    private val viewModel: SelectRecipesViewModel,
    val scaffoldState: ScaffoldState,
) {
    val uiState: SelectRecipesUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favoriteRecipeIds: List<Int>
        @Composable get() = viewModel.favoriteRecipeIds.collectAsState().value


//    val selectedRecipes: List<RecipeThumb>
//        @Composable get() = viewModel.selectedRecipes.collectAsState().value.mapBoth(
//            success = { it },
//            failure = { emptyList() }
//        )

    fun onTabClicked(selectedTab: CategoryTab) = viewModel.onTabClicked(selectedTab)

    fun resetMessage() = viewModel.resetMessage()

    fun selectRecipe(recipe: Recipe, ingredients: List<Ingredient>) = viewModel.selectRecipe(recipe, ingredients)

    fun removeRecipe(id: Int) = viewModel.removeRecipe(id)

    fun addMenu() = viewModel.addMenu()

    fun addFavorite(id: Int) = viewModel.addFavorite(id)

    fun removeFavorite(id: Int) = viewModel.removeFavorite(id)

}

@Composable
fun rememberSelectRecipesState(
    viewModel: SelectRecipesViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
/*
    conditions: String?,
    viewModel: SelectRecipesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = SelectRecipesViewModel.Factory(conditions)
    )
*/
): SelectRecipesState = remember {
    SelectRecipesState(viewModel, scaffoldState)
}