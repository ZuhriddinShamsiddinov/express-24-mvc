package uz.jl.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import uz.jl.services.AuthService;

@Configuration
@ComponentScan("uz.jl")
@EnableWebSecurity
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    public static final String[] WHITE_LIST = new String[]{
            "/",
            "/auth/login",
            "/auth/register",
            "/static/**",
            "/product/category/*",
            "/category/display/**",
            "/product/display/**"
    };

    @Value("${spring.security.rememberme.secret.key}")
    public String SECRET_KEY;

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .antMatchers(WHITE_LIST).permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login")
                                .usernameParameter("uname")
                                .passwordParameter("pswd")
                                .defaultSuccessUrl("/", false))
                .rememberMe(httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer
                                .rememberMeParameter("rememberMe")
                                .rememberMeCookieName("remember")
                                .tokenValiditySeconds(10 * 86400)
                                .key(SECRET_KEY))
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutRequestMatcher(
                                        new AntPathRequestMatcher("/auth/logout", "POST", true)
                                )
                                .logoutSuccessUrl("/auth/login")
                                .deleteCookies("JSESSIONID", "remember")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                );
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
