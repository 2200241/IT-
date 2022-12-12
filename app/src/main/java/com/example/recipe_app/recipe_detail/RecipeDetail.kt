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
import com.example.recipe_app.data.Recipe

@Composable
fun RecipeDetail(
    state: RecipeDetailState = rememberRecipeDetailState(),
    paddingValues: PaddingValues,
    addButtonIsDisplayed: Boolean = false,
    onBackPressed: () -> Unit = {}
) {
    val uiState = state.uiState

    Column(
        modifier = Modifier.padding(paddingValues),
    ) {
        LazyColumn() {
            item { CookingImage(recipe = uiState.recipe) }
            item { RecipeDetailTitle("材料(人分)") }
            item { RecipeDetailMaterials() }
            item { RecipeDetailTitle("作り方") }
            item { CookingProcedure() }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }

    RecipeDetailBottomButtons(
        paddingValues = paddingValues,
        recipeId = uiState.recipe.id,
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
            .padding(bottom = 5.dp)
            .height(220.dp)
            .background(color = Color.LightGray)
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp)
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
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.fontColor)
    )
}

@Composable
fun RecipeDetailMaterials() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        for (i in 1..10) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "材料名$i",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.fontColor)
                )
                Text(
                    text = "分量$i",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.fontColor)
                )
            }
            Divider(
                modifier = Modifier.padding(bottom = 10.dp),
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun CookingProcedure() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        for (i in 1..5) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "$i.",
                fontSize = 20.sp,
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
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (addButtonIsDisplayed) {
                ExtendedFloatingActionButton(
                    backgroundColor = colorResource(id = R.color.addListButtonColor),
                    contentColor = Color.White,
                    text = {
                        Text(
                            text = "買い物リストへ追加",
                            fontSize = 18.sp
                        )
                    },
                    onClick = onAddButtonClicked
                )
            }
            FloatingActionButton(
                backgroundColor = Color.White,
                contentColor = Color.LightGray,
                onClick = if (favoriteRecipeIds.contains(recipeId)) onUnliked else onLiked
            ) {
                Icon(
                    Icons.Sharp.Favorite,
                    contentDescription = null,
                    tint = if (favoriteRecipeIds.contains(recipeId))
                        colorResource(id = R.color.favoriteIconColor)
                    else Color.LightGray
                )
            }
        }
    }
}