package com.raji.data.mapper

import com.raji.core.functional.mapOrDefault
import com.raji.core.functional.orDefault
import com.raji.core.mapper.ResultMapper
import com.raji.data.dto.detail.EmailDetailsDto
import com.raji.data.dto.detail.RecipientInfo
import com.raji.data.dto.detail.SenderInfo
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.domain.model.emaildetails.FileInfo
import com.raji.domain.model.emaildetails.RecipientModel
import com.raji.domain.model.emaildetails.SenderInfoModel
import javax.inject.Inject

class EmailDetailsMapper @Inject constructor() :
    ResultMapper<List<EmailDetailsDto>, EmailDetailsModel> {
    override fun map(input: List<EmailDetailsDto>): EmailDetailsModel =
        input.first().toEmailDetailsModel()

}

private fun EmailDetailsDto.toEmailDetailsModel(): EmailDetailsModel {
    return EmailDetailsModel(
        id = id,
        from = payload.senderInfo.toSenderInfoModel(),
        to = payload.to.toRecipientModel(),
        cc = payload.cc.toRecipientModel(),
        bcc = payload.bcc.toRecipientModel(),
        subject = payload.subject.orEmpty(),
        htmlBody = body?.html ?: body?.text ?: "",
        date = payload.date.orEmpty(),
        isImportant = isImportant.orDefault(),
        isStarred = labels.contains("Starred"),
        isPromotional = isPromotional.orDefault(),
        fileInfo =
        payload.attachments.mapOrDefault(emptyList()) {
            FileInfo(
                filename = it!!.filename.orEmpty(),
                mimeType = it.mimeType.orEmpty(),
                size = it.size ?: 0L,
                downLoadUrl = it.downloadUrl
            )
        },
        labels =
        labels.mapOrDefault(emptyList()) {
            it!!
        }
    )
}


private fun SenderInfo.toSenderInfoModel(): SenderInfoModel {
    return SenderInfoModel(
        email = email,
        name = name,
        profileImage = profileImage
    )
}

private fun List<RecipientInfo>?.toRecipientModel(): List<RecipientModel> {
    return this.mapOrDefault {
        RecipientModel(
            email = it.email,
            name = it.name
        )
    }
}