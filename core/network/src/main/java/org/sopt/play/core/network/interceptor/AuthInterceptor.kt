package org.sopt.play.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenProvider: () -> String = { "" }
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath
        val method = request.method

        // 인증(Authorization) 헤더가 필요 없는 예외 조건 (Early Return)
        val isNoAuthRequest = method.equals("GET", ignoreCase = true) || path.contains("/members/authorization-code")

        if (isNoAuthRequest) {
            return chain.proceed(request)
        }

        val token = tokenProvider()
        if (token.isBlank()) {
            return chain.proceed(request)
        }

        val authenticatedRequest = request.newBuilder()
            .header("Authorization", token)
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
