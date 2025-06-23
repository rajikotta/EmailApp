package com.raji.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.domain.model.emaildetails.RecipientModel
import com.raji.domain.model.emaildetails.SenderInfoModel

class EmailDetailScreenshotTest {

    @Preview
    @Composable
    fun EmailDetailsLoadingScreenTest() {
        EmailDetailsScreen(
            from = "from",
            profileImage = "profileImage",
            subject = "subject",
            isPromotional = true,
            state = EmailDetailContract.DetailUiState()
        )
    }


    @Preview
    @Composable
    fun EmailDetailsErrorScreenTest() {
        EmailDetailsScreen(
            from = "from",
            profileImage = "profileImage",
            subject = "subject",
            isPromotional = true,
            state = EmailDetailContract.DetailUiState(loading = false, isError = true)
        )
    }


    @Preview
    @Composable
    fun EmailDetailsSuccessScreenTest() {
        EmailDetailsScreen(
            from = "from",
            profileImage = "profileImage",
            subject = "subject",
            isPromotional = true,
            state = EmailDetailContract.DetailUiState(
                loading = false, isError = false, emailDetail = EmailDetailsModel(
                    id = "id",
                    from = SenderInfoModel(
                        name = "from name",
                        email = "from@email.com",
                        profileImage = "profileImage"
                    ),
                    to = listOf(
                        RecipientModel(
                            name = "test name to",
                            email = "to@email.com"
                        )
                    ),
                    cc = listOf(
                        RecipientModel(
                            name = "test name cc",
                            email = "cc@email.com"
                        )
                    ),
                    bcc = listOf(
                        RecipientModel(
                            name = "test name bcc",
                            email = "bcc@email.com"
                        )
                    ),
                    subject = "subject",
                    htmlBody = "htmlBody",
                    date = "date",
                    isImportant = true,
                    isStarred = true,
                    isPromotional = true,
                    fileInfo = emptyList(),
                    labels = listOf("labels")
                )
            )
        )
    }
}