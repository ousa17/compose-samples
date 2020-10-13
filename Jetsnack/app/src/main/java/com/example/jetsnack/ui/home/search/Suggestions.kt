/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.home.search

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetsnack.model.SearchRepo
import com.example.jetsnack.model.SearchSuggestionGroup
import com.example.jetsnack.ui.components.JetsnackSurface
import com.example.jetsnack.ui.theme.JetsnackTheme

@OptIn(ExperimentalLazyDsl::class)
@Composable
fun SearchSuggestions(
    suggestions: List<SearchSuggestionGroup>,
    onSuggestionSelect: (String) -> Unit
) {
    LazyColumn {
        suggestions.forEach { suggestionGroup ->
            item {
                SuggestionHeader(suggestionGroup.name)
            }
            items(suggestionGroup.suggestions) { suggestion ->
                Suggestion(suggestion, onSuggestionSelect)
            }
            item {
                Spacer(Modifier.preferredHeight(4.dp))
            }
        }
    }
}

@Composable
private fun SuggestionHeader(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h6,
        color = JetsnackTheme.colors.textPrimary,
        modifier = Modifier
            .preferredHeightIn(min = 56.dp)
            .padding(horizontal = 24.dp, vertical = 4.dp)
            .wrapContentHeight()
    )
}

@Composable
private fun LazyItemScope.Suggestion(
    suggestion: String,
    onSuggestionSelect: (String) -> Unit
) {
    Text(
        text = suggestion,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .fillParentMaxWidth()
            .preferredHeightIn(min = 48.dp)
            .clickable { onSuggestionSelect(suggestion) }
            .padding(start = 24.dp)
            .wrapContentSize(Alignment.CenterStart)
    )
}

@Preview
@Composable
fun PreviewSuggestions() {
    JetsnackTheme {
        JetsnackSurface {
            SearchSuggestions(
                suggestions = SearchRepo.getSuggestions(),
                onSuggestionSelect = { }
            )
        }
    }
}
