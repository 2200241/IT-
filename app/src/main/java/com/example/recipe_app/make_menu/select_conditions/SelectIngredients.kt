package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R

@Composable
fun SelectIngredients(
    modifier: Modifier = Modifier,
    keywords: String,
    setKeywords: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            SearchTextField(
                keywords = keywords,
                setKeywords = setKeywords
            )
        }
        item { Divider(color = Color.LightGray) }
        item {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                text = "食材一覧",
                fontSize = 20.sp,
                color = Color(0xFF333333)
            )
        }
        for (i in 1..5) {
            item {
                Column { CategoryTitle("カテゴリー名$i") }
            }
        }
    }
}

@Composable
fun SearchTextField(
    keywords: String = "",
    setKeywords: (String) -> Unit
) {
//    val text = remember { mutableStateOf("") }
    BasicTextField(
        modifier = Modifier.padding(15.dp),
        value = keywords,
        onValueChange = { setKeywords(it) },
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F2F2), RoundedCornerShape(50))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Sharp.Search,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
                Spacer(Modifier.width(5.dp))
                if (keywords.isEmpty()) {
                    Text(
                        text = "検索",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                } else {
                    innerTextField()
                }
            }
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
            //.padding(10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        //backgroundColor = colorResource(id = R.color.gray),
        border = BorderStroke(1.5.dp, colorResource(id = R.color.gray)),
        //shape = MaterialTheme.shapes.medium,
        //elevation = 10.dp,
        onClick = {
            expandedState.value = !expandedState.value
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f)
                        .padding(start = 15.dp),
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
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
                        tint = Color(0xFF333333)
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
    for (i in 1..5) {
        val checkedState = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 6.dp)
                .clickable { checkedState.value = !checkedState.value },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "食材名$i",
                fontSize = 20.sp,
                //fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            Checkbox(
                modifier = Modifier.size(40.dp),
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
    }
}