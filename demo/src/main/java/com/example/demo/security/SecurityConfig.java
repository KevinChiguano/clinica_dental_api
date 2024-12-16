package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// @EnableMethodSecurity
public class SecurityConfig {

    /*
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
     * throws Exception {
     * 
     * return httpSecurity
     * .csrf(csrf -> csrf.disable())
     * .httpBasic(Customizer.withDefaults())
     * .sessionManagement(session ->
     * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     * .authorizeHttpRequests(http -> {
     * //Configurar los endpoints publicos
     * http.requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("administrador");
     * 
     * //Configurar los endpoints privados
     * http.requestMatchers(HttpMethod.POST, "/usuario/**").permitAll();
     * 
     * //Configurar el resto de los endpointd
     * http.anyRequest().authenticated();
     * })
     * .build();
     * 
     * }
     */

    /*
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
     * throws Exception {
     * 
     * return httpSecurity
     * .csrf(csrf -> csrf.disable())
     * .httpBasic(Customizer.withDefaults())
     * .sessionManagement(session ->
     * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     * .build();
     * 
     * }
     */

    /*
     * @Bean
     * public AuthenticationManager
     * authenticationManager(AuthenticationConfiguration
     * authenticationConfiguration)
     * throws Exception {
     * return authenticationConfiguration.getAuthenticationManager();
     * }
     */

    /*
     * @Bean
     * public AuthenticationProvider authenticationProvider(UserDetailServiceImpl
     * userDetailService) {
     * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
     * provider.setPasswordEncoder(passwordEncoder());
     * provider.setUserDetailsService(userDetailService);
     * return provider;
     * }
     */

    // Usar solo en pruebas no en produccion
    /*
     * @Bean
     * public PasswordEncoder passwordEncoder() {
     * return NoOpPasswordEncoder.getInstance();
     * }
     */

    /*
     * @Bean
     * public PasswordEncoder passwordEncoder() {
     * return new BCryptPasswordEncoder();
     * }
     */

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // Verifica la informacion de los usuarios
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Encriptar contraseña
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Incorpora el filtro de seguridad de JWT
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // Establecer una cadena de filtros de seguridad
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
