package com.example.recipe_app.make_menu.select_recipes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.data.RecipeWithCategoryId
import com.example.recipe_app.data.RecipeThumb
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectRecipes(
    state: SelectRecipesState,
    paddingValues: PaddingValues,
    onItemClicked: (Int, String) -> Unit,
    onBackPressed: () -> Unit
) {
    val uiState = state.uiState

    if (uiState.message.isNotBlank()) {
        LaunchedEffect(state.scaffoldState.snackbarHostState) {
            state.scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.message,
                actionLabel = "OK"
            )
            state.resetMessage()
        }
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.result),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.fontColor)
            )
            Divider(color = Color.LightGray)
            LazyColumn {
                item {
                    Text(
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                        text = "献立",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.fontColor)
                    )
                }
                item {
                    if (state.selectedRecipes.isNotEmpty()) {
                        SelectedRecipes(true, state.selectedRecipes, onItemClicked, state::removeRecipe)
                    } else {
                        SelectedRecipesIsEmpty()
                    }
                }
                item { Divider(color = Color.LightGray) }
                stickyHeader {
                    SelectCategoriesTabBar(
                        selectedTab = uiState.selectedTab,
                        onClick = state::onTabClicked
                    )
                }
                item {
                    SearchResultRecipes(
                        recipeWithCategoryIds = uiState.recipeWithCategoryIds.filter { it.categoryId == uiState.selectedTab.index + 1 },
                        favoriteRecipeIds = state.favoriteRecipeIds,
                        onItemClicked = onItemClicked,
                        onRecipeLiked = state::addFavorite,
                        onRecipeUnLiked = state::removeFavorite
                    )
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }

        SelectRecipesBottomButton(
            paddingValues = paddingValues,
            onAddMenuClicked = { state.addMenu() }
        )
    }
}

@Composable
fun SelectedRecipes(
    deleteButtonIsDisplayed: Boolean = false,
    selectedRecipes: List<RecipeThumb>,
    onItemClicked: (Int, String) -> Unit,
    onDeleteClicked: (Int) -> Unit = {}
) {
    LazyRow(
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        selectedRecipes.forEach { recipe ->
            item {
                Box(
                    modifier = Modifier
                        .size(110.dp, 75.dp)
                        .background(color = Color.LightGray)
                        .clickable { onItemClicked(recipe.id, recipe.thumb) }
                ) {
                    Text(text = "料理画像", color = Color.White)
                    //Image(painter = , contentDescription = )

                    if (deleteButtonIsDisplayed) {
                        FloatingActionButton(
                            modifier = Modifier
                                .size(25.dp)
                                .absoluteOffset(5.dp, (-5).dp)
                                .align(alignment = Alignment.TopEnd),
                            backgroundColor = Color.DarkGray,
                            contentColor = Color.White,
                            onClick = { onDeleteClicked(recipe.id) }
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.Clear,
                                contentDescription = "Clear",
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectedRecipesIsEmpty() {
    Box(modifier = Modifier.padding(12.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "レシピが追加されていません",
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "献立に追加されたレシピの一覧がここに表示されます",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun SelectCategoriesTabBar(
    modifier: Modifier = Modifier,
    selectedTab: CategoryTab,
    onClick: (CategoryTab) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier.padding(horizontal = 5.dp),
        selectedTabIndex = selectedTab.index,
        backgroundColor = Color.White,
        contentColor = Color.Transparent,
        edgePadding = 0.dp,
    ) {
        CategoryTabs.forEach { item ->
            val selected = selectedTab.index == item.index
            val backgroundColor =
                if (selected) colorResource(id = R.color.categoryTabColor)
                else Color.Transparent
            val borderColor =
                if (selected) colorResource(id = R.color.categoryTabColor)
                else colorResource(id = R.color.borderLightColor)

            Tab(
                modifier = modifier
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .border(border = BorderStroke(1.5.dp, borderColor), shape = CircleShape),
                selected = selected,
                selectedContentColor = Color.White,
                unselectedContentColor = colorResource(id = R.color.categoryTabColor),
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    modifier = modifier.padding(vertical = 8.dp),
                    text = stringResource(id = item.titleId),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private val CategoryTabs = listOf(
    CategoryTab.SelectStapleFoodTab,
    CategoryTab.SelectMainDishTab,
    CategoryTab.SelectSideDishTab,
    CategoryTab.SelectSoupTab,
    CategoryTab.SelectSweetsTab,
    CategoryTab.SelectDrinkTab,
    CategoryTab.SelectOthersTab
)

@Composable
fun SearchResultRecipes(
    recipeWithCategoryIds: List<RecipeWithCategoryId>,
    favoriteRecipeIds: List<Int>,
    onItemClicked: (Int, String) -> Unit,
    onRecipeLiked: (RecipeWithCategoryId) -> Unit,
    onRecipeUnLiked: (Int) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    FlowRow(
        modifier = Modifier.padding(start = 7.5.dp),
        mainAxisSpacing = 5.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        recipeWithCategoryIds.map { recipe ->
            Box(
                modifier = Modifier
                    .size(((screenWidth / 2) - 10).dp, 140.dp)
                    .background(color = Color.LightGray)
                    .clickable { onItemClicked(recipe.id, recipe.thumb) }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .size(50.dp)
                            .align(alignment = Alignment.End)
                            .padding(top = 8.dp, end = 8.dp),
                        backgroundColor = Color.White,
                        onClick = {
                            if (favoriteRecipeIds.contains(recipe.id)) {
                                onRecipeUnLiked(recipe.id)
                            } else {
                                onRecipeLiked(recipe)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp),
                            tint = if (favoriteRecipeIds.contains(recipe.id))
                                colorResource(id = R.color.favoriteIconColor)
                            else Color.LightGray
                        )
                    }
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = recipe.title,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun SelectRecipesBottomButton(
    paddingValues: PaddingValues,
    onAddMenuClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.BottomCenter
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp),
            backgroundColor = colorResource(id = R.color.decisionButtonColor),
            contentColor = Color.White,
            text = {
                Text(
                    text = "献立決定",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            onClick = onAddMenuClicked
        )
    }
}