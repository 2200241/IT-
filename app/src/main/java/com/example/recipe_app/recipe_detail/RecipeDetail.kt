package com.example.recipe_app.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.data.Recipe

@Composable
fun RecipeDetail(
    state: RecipeDetailState = rememberRecipeDetailState(),
    paddingValues: PaddingValues,
    addButtonIsDisplayed: Boolean = false,
    onBackPressed: () -> Unit = {}
) {
    val uiState = state.uiState
    val recipe = uiState.recipe

    Column(modifier = Modifier.padding(paddingValues)) {
        LazyColumn {
            item { CookingImage(recipe = recipe) }
            item { RecipeDetailTitle("材料(${recipe.serving}人分)") }
            item { RecipeDetailMaterials(recipe.ingredients) }
            item { RecipeDetailTitle("作り方") }
            item { CookingProcedure(recipe.instructions) }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }

    RecipeDetailBottomButtons(
        paddingValues = paddingValues,
        recipeId = recipe.id,
        favoriteRecipeIds = state.favoriteRecipeIds,
        addButtonIsDisplayed = addButtonIsDisplayed,
        onAddButtonClicked = state::addToTempMenu,
        onLiked = state::addFavoriteRecipe,
        onUnliked = state::removeFavoriteRecipe
    )
}

@Composable
fun CookingImage(recipe: Recipe) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(color = Color.LightGray)
    ) {
        Text(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomStart),
            text = recipe.title,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun RecipeDetailTitle(title: String) {
    Text(
        modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 3.dp),
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.fontColor)
    )
}

@Composable
fun RecipeDetailMaterials(
    ingredients: List<Ingredient>
) {
    Column {
        ingredients.forEach { ingredients ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ingredients.name,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.fontColor)
                )
                Text(
                    text = ingredients.quantity,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.fontColor)
                )
            }
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun CookingProcedure(
    instructions: List<Instruction>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        instructions.forEach { instruction ->
            Text(
                text = "${instruction.order}. ${instruction.content}",
                fontSize = 16.sp,
                color = colorResource(id = R.color.fontColor)
            )
        }
    }
}

@Composable
fun RecipeDetailBottomButtons(
    paddingValues: PaddingValues,
    recipeId: Int,
    favoriteRecipeIds: List<Int>,
    addButtonIsDisplayed: Boolean,
    onAddButtonClicked: () -> Unit,
    onLiked: () -> Unit,
    onUnliked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (addButtonIsDisplayed) {
                ExtendedFloatingActionButton(
                    modifier = Modifier.weight(1f),
                    backgroundColor = colorResource(id = R.color.addListButtonColor),
                    contentColor = Color.White,
                    text = {
                        Text(
                            text = "献立に追加",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = onAddButtonClicked
                )
                Spacer(Modifier.width(12.dp))
            } else {
                Spacer(Modifier.weight(1f))
            }
            FloatingActionButton(
                backgroundColor = Color.White,
                onClick = if (favoriteRecipeIds.contains(recipeId)) onUnliked else onLiked
            ) {
                Icon(
                    imageVector = Icons.Sharp.Favorite,
                    contentDescription = "favorite",
                    tint = if (favoriteRecipeIds.contains(recipeId))
                        colorResource(id = R.color.favoriteIconColor)
                    else Color.LightGray
                )
            }
        }
    }
}