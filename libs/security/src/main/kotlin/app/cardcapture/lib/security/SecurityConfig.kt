package app.cardcapture.lib.security

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@ConditionalOnClass(HttpSecurity::class)
class SecurityConfig(
) {

    companion object {
        private val WHITE_LIST = arrayOf(
            "/actuator/**",
            "/api/v1/dev/auth/**",
            "/api/v1/auth/**"
        )
    }
    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint::class)
    fun authEntryPoint(): AuthenticationEntryPoint {
        return CustomAuthenticationEntryPoint()
    }


    @Bean
    fun filterChain(http: HttpSecurity, jwtDecoder: JwtDecoder, authEntryPoint : AuthenticationEntryPoint): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers(*WHITE_LIST).permitAll()
                it.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2ResourceServer { rs ->
                rs.jwt { it.decoder(jwtDecoder) }
                rs.authenticationEntryPoint(authEntryPoint)
            }
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val cfg = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("*")
            exposedHeaders = listOf("Authorization")
            allowCredentials = false
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", cfg)
        return source
    }
}
