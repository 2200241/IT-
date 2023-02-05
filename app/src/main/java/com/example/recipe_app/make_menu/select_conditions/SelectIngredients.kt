package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SelectIngredients(
    modifier: Modifier = Modifier,
    keywords: String,
    setKeywords: (String) -> Unit,
    ingredients: Map<Int, String> = emptyMap(),
    selectedIngredients: List<Int> = emptyList(),
    onItemClicked: (Int) -> Unit,
    onSearchClicked: () -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            SearchTextField(
                keywords = keywords,
                setKeywords = setKeywords,
                onClicked = onSearchClicked
            )
        }
        item { Divider(color = Color.LightGray) }
        item {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 3.dp),
                text = "食材一覧",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.fontColor)
            )
        }
        item { Divider(color = Color.LightGray) }
        for (i in 1..5) {
            item { CategoryTitle("カテゴリー名$i") }
            item { Divider(color = Color.LightGray) }
        }
    }
}

@Composable
fun SearchTextField(
    keywords: String = "",
    setKeywords: (String) -> Unit,
    onClicked:() -> Unit,
) {
    BasicTextField(
        modifier = Modifier.padding(15.dp),
        value = keywords,
        onValueChange = { setKeywords(it) },
        textStyle = TextStyle.Default.copy(fontSize = 17.sp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.searchTextFieldColor), CircleShape)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Sharp.Search,
                    contentDescription = "Search",
                    tint = Color.DarkGray
                )
                Spacer(Modifier.width(5.dp))
                if (keywords.isEmpty()) {
                    Text(
                        text = "キーワード",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                } else {
                    innerTextField()
                }
            }
            ExtendedFloatingActionButton(
//                modifier = Modifier.weight(1f),
                backgroundColor = colorResource(id = R.color.searchButtonColor),
                contentColor = Color.White,
                text = {
                    Text(
                        text = "候補表示",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = onClicked
            )
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryTitle(title: String) {
    val expandedState = remember { mutableStateOf(false) }
    val rotationState = animateFloatAsState(
        targetValue = if (expandedState.value) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState.value = !expandedState.value
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f)
                        .padding(start = 15.dp),
                    text = title,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.fontColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState.value),
                    onClick = {
                        expandedState.value = !expandedState.value
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                        tint = Color.DarkGray
                    )
                }
            }
            if (expandedState.value) {
                Ingredients()
            }
        }
    }
}

@Composable
fun Ingredients() {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    FlowRow(mainAxisAlignment = MainAxisAlignment.Start) {
        for (i in 1 .. 5) {
            val checkedState = remember { mutableStateOf(false) }
            Box(modifier = Modifier.width(((screenWidth / 2)).dp)) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                            .clickable { checkedState.value = !checkedState.value },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "食材名",
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.fontColor)
                        )
                        Box(modifier = Modifier.size(30.dp)) {
                            Checkbox(
                                modifier = Modifier
                                    .absoluteOffset(5.dp, 0.dp)
                                    .size(40.dp),
                                colors = CheckboxDefaults.colors(colorResource(id = R.color.selectButtonColor)),
                                checked = checkedState.value,
                                onCheckedChange = { checkedState.value = it }
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.LightGray
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}