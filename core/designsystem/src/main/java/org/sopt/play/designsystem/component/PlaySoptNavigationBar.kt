package org.sopt.play.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.navigation.MainTab
import org.sopt.play.core.ui.ext.customInsets
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptNavigationBar(
    modifier: Modifier = Modifier,
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(PlaySoptTheme.color.white)
            .border(
                width = 1.dp,
                color = PlaySoptTheme.color.gray2,
            )
            .customInsets(bottom = true),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MainTab.entries.forEach { tab ->
            PlaySoptNavigationItem(
                modifier = Modifier.weight(1f),
                type = tab,
                isSelected = tab == currentTab,
                onClick = { onTabSelected(tab) },
            )
        }
    }
}

@Composable
private fun PlaySoptNavigationItem(
    modifier: Modifier = Modifier,
    type: MainTab,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .nullIndication(onClick = onClick)
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            painter = painterResource(id = if (isSelected) type.selectedIcon else type.unSelectedIcon),
            contentDescription = type.label,
            tint = PlaySoptTheme.color.black,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = type.label,
            style = PlaySoptTheme.typography.sb14,
        )
    }
}

@Preview
@Composable
private fun PlaySoptNavigationBarPreview() {
    PlaySoptPreviewTheme {
        PlaySoptNavigationBar(
            currentTab = MainTab.Home,
            onTabSelected = {},
        )
    }
}