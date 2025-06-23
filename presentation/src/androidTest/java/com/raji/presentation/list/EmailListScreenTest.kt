package com.raji.presentation.list

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.raji.core.error.Failure
import com.raji.domain.model.emaillist.EmailListItemModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailListScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val composeTestRule2 = createComposeRule()

    @Test
    fun testErrorState() {
        val state = EmailListContract.EmailListState.Error(
            Failure.ServerError(500, "Server Error")
        )
        val dispatch: (EmailListContract.EmailListEvent) -> Unit = {}

        composeTestRule.setContent {
            EmailListScreen(
                state = state,
                dispatch = { },
            )
        }

        composeTestRule.onNodeWithText("Server Error").isDisplayed()
    }


    @Test
    fun testSuccessState() {
        val emailList = listOf(
            EmailListItemModel(
                id = "1",
                from = "test@example.com",
                subject = "Test Subject",
                snippet = "Test Snippet",
                isStarred = false,
                date = "o",
                profileImage = "",
                isPromotional = false,
                isImportant = false
            ),
            EmailListItemModel(
                id = "2",
                from = "test@example.com",
                subject = "Test Subject 2",
                snippet = "Test Snippet",
                isStarred = false,
                date = "o",
                profileImage = "",
                isPromotional = false,
                isImportant = true
            ),
        )
        val state = EmailListContract.EmailListState.Success(emailList)
        val dispatch: (EmailListContract.EmailListEvent) -> Unit = {}

        composeTestRule.setContent {
            EmailListScreen(
                state = state,
                dispatch = dispatch,
            )
        }

        with(composeTestRule) {
            onNodeWithText("Test Subject").assertExists()
            onNodeWithText("Test Subject").assert(hasClickAction())
            onNodeWithText("Test Subject 2").assertExists()
            onNodeWithText("Test Subject 2").assert(hasClickAction())
        }
    }


    @Test
    fun testLoadingState() {
        val state = EmailListContract.EmailListState.Loading
        val effect: EmailListContract.EmailListEffect? = null
        val dispatch: (EmailListContract.EmailListEvent) -> Unit = {}

        composeTestRule.setContent {
            EmailListScreen(
                state = state,
                dispatch = dispatch,
            )
        }

        composeTestRule.onNodeWithContentDescription("Loading").assertExists()
    }


}