package com.ohgiraffers.security.config;


import com.ohgiraffers.security.config.handler.AuthFailHandler;
import com.ohgiraffers.security.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthFailHandler failHandler;
    @Autowired
    public SecurityConfig(AuthFailHandler failHandler) {
        this.failHandler = failHandler;
    }

    /*
    * 비밀번호 인코딩을 하기 위한 bean
    * Bcrypt는 비밀번호 해싱에 가장 많이 사용되는 알고리즘 중 하나이다.
    * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // bcrypt 알고리즘을 통해서 암호화를 시킴

    }

    // 정적 리소스는 제거하고 보안처리 하지 말라는 의미
    //
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }

    // 로그인 이전에 사용할 수 있는 페이지를 만들어줌
    //권한이 필요없는 페이지를 등록해주는 것 : permitAll
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->{
            auth.requestMatchers("/auth/login", "user/signup","/auth/fail","/").permitAll();
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());
            auth.anyRequest().authenticated();
        }).formLogin(login ->{
            login.loginPage("/auth/login");
            login.usernameParameter("user"); // 전달되는 키값을 입력해줌
            login.passwordParameter("pass");
            login.defaultSuccessUrl("/");
            login.failureHandler(failHandler);
//          Login html 에  <input type="password" id="pass" name="pass"/> 와 연결됨
//            login.defaultSuccessUrl("/",true);
//            login.failureUrl("/auth/fail");

        }).logout(logout ->{
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            // 로그아웃과 동시에  jessionid 를 지우겠다는 의미
            logout.logoutSuccessUrl("/");
        }).sessionManagement(session -> {
            session.maximumSessions(1);
            // 이 계정으로  session 을 몇 개 만들 수 있는지 설정해줌
            session.invalidSessionUrl("/");
            // 튕겼을 때 어느 페이지로 보여주겠다는 링크
        }).csrf(csrf -> csrf.disable());
            //마치 내가 아닌 것처럼 변조된 공격들을 날리는 것을 활성화시키지 않겠다(disable).

        return http.build();
    }


}
