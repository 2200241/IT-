package com.example.recipe_app.make_menu.select_recipes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.make_menu.select_conditions.ConditionTab
import com.example.recipe_app.make_menu.select_conditions.SelectTags
import com.example.recipe_app.data.Recipe
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SelectRecipes(
    state: SelectRecipesState = rememberSelectRecipesState(),
    paddingValues: PaddingValues,
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val uiState = state.uiState

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
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.result),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.fontColor)
            )

            Divider(color = Color.LightGray)
            SelectedRecipes(onItemClicked)
            Divider(color = Color.LightGray)

            SelectCategoriesTabBar(
                selectedTab = uiState.selectedTab,
                onClick = state::onTabClicked
            )

            LazyColumn() {
                when (uiState.selectedTab) {
                    CategoryTab.SelectStapleFoodTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                    CategoryTab.SelectMainDishTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                    CategoryTab.SelectSideDishTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                    CategoryTab.SelectSoupTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                    CategoryTab.SelectSweetsTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                    CategoryTab.SelectDrinkTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                    CategoryTab.SelectOthersTab -> {
                        item { SearchResultRecipes(uiState.recipes, onItemClicked) }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                backgroundColor = colorResource(id = R.color.decisionButtonColor),
                contentColor = Color.White,
                text = {
                    Text(
                        text = "この献立で決定",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = {}
            )
        }
    }
}

@Composable
fun SelectedRecipes(
    onItemClicked: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 1..5) {
            item {
                Box(
                    modifier = Modifier
                        .size(110.dp, 75.dp)
                        .background(color = Color.LightGray)
                        .clickable { onItemClicked("testId") }
                ) {
                    Text(text = "料理画像", color = Color.White)
                    FloatingActionButton(
                        modifier = Modifier
                            .size(18.dp)
                            .align(alignment = Alignment.TopEnd),
                        backgroundColor = Color.DarkGray,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Sharp.Clear,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
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
        modifier = modifier,
        selectedTabIndex = selectedTab.index,
        backgroundColor = Color.White,
        edgePadding = 0.dp
    ) {
        CategoryTabs.forEach { item ->
            Tab(
                modifier = Modifier.height(50.dp),
                selected = item.index == selectedTab.index,
                selectedContentColor = colorResource(id = R.color.fontColor),
                unselectedContentColor = Color.Gray,
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    text = stringResource(id = item.titleId),
                    fontSize = 18.sp,
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
    recipes: List<Recipe>,
    onItemClicked: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    FlowRow(
        modifier = Modifier.padding(start = 7.5.dp),
        mainAxisSpacing = 5.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        recipes.map { recipe ->
            Box(
                modifier = Modifier
                    .size(((screenWidth / 2) - 10).dp, 140.dp)
                    .background(color = Color.LightGray)
                    .clickable { onItemClicked(recipe.id) }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        modifier = Modifier.align(alignment = Alignment.End),
                        onClick = { Log.d("Button", "onClick") }
                    ) {
                        Icon(
                            Icons.Sharp.Favorite,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    /*FloatingActionButton(
                        modifier = Modifier
                            .size(20.dp)
                            .align(alignment = Alignment.End)
                            .padding(16.dp),
                        backgroundColor = Color.White,
                        contentColor = Color.LightGray,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Sharp.Favorite,
                            contentDescription = null,
                        )
                    }*/
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