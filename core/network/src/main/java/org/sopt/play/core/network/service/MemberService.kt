package org.sopt.play.core.network.service

import org.sopt.play.core.network.model.AuthCodeDataDto
import org.sopt.play.core.network.model.BaseResponse
import org.sopt.play.core.network.model.IssueAuthCodeRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {

    @POST("api/v1/members/authorization-code")
    suspend fun getAuthorizationCode(
        @Body request: IssueAuthCodeRequestDto
    ): BaseResponse<AuthCodeDataDto>
}
