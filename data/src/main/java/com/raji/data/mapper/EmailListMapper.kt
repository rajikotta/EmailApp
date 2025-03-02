package com.raji.data.mapper

import com.raji.core.functional.orDefault
import com.raji.core.mapper.ResultMapper
import com.raji.data.dto.list.EmailListItemDto
import com.raji.domain.model.emaillist.EmailListItemModel

class EmailListMapper : ResultMapper<List<EmailListItemDto>, List<EmailListItemModel>> {
    override fun map(input: List<EmailListItemDto>): List<EmailListItemModel> {
      return  input.filter { it.id != null && it.payload.from != null }
            .map { it.toModel() }
    }
}

fun EmailListItemDto.toModel() = EmailListItemModel(
    id = id!!,
    from = payload.from!!,
    subject = payload.subject.orEmpty(),
    profileImage = payload.profileImage,
    snippet = snippet.orEmpty(),
    date = payload.date.orEmpty(),
    isImportant = isImportant.orDefault(),
    isStarred = isImportant.orDefault(),
    isPromotional = isPromotional.orDefault()
)