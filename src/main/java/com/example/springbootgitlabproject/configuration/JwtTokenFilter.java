package com.example.springbootgitlabproject.configuration;

import com.example.springbootgitlabproject.domain.entity.Teacher;
import com.example.springbootgitlabproject.service.TeacherService;
import com.example.springbootgitlabproject.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TeacherService teacherService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 권한 주거나 주지 않음
        // 개찰구와 같은 역할
        // 현재는 모두 닫혀 있는데, 열어보겠습니다.

        // 변조가 되면 안되므로 토큰은 final 로 선언한다.
        // 토큰 생성 과정:
        // 1. 인증을 위한 POST 요청의 헤더 중 authorization 헤더를 먼저 찾는다.
        // 2.
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader:{}", authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token;

        try {
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            log.error("token 추출에 실패 했습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        /* 토큰이 만료되었는지 확인한다. */
        if (JwtTokenUtils.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 아이디를 꺼낸다.
        String userName = JwtTokenUtils.getUserName(token, secretKey);


        Teacher user = teacherService.getTeacherByUserName(userName);
        log.info("role:{}", user.getUserRole());


        // TODO: 문 열기
        // 인증이 성공했을 때 Authentication 객체가 SecurityContextHolder 에 저장되는데, 왜?
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", null,
                List.of(new SimpleGrantedAuthority(user.getUserRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication();


    }
}
