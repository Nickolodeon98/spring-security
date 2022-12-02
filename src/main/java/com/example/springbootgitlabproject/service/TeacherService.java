package com.example.springbootgitlabproject.service;

import com.example.springbootgitlabproject.configuration.EncoderConfiguration;
import com.example.springbootgitlabproject.domain.dto.TeacherRequestDto;
import com.example.springbootgitlabproject.domain.entity.Teacher;
import com.example.springbootgitlabproject.domain.dto.TeacherResponseDto;
import com.example.springbootgitlabproject.exception.ErrorCode;
import com.example.springbootgitlabproject.exception.TeacherException;
import com.example.springbootgitlabproject.repository.TeacherRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service // @Configuration + @Bean / @Component 도 사용 가능하다
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder pwdEncoder;

    public TeacherService(TeacherRepository teacherRepository, BCryptPasswordEncoder pwdEncoder) {
        this.teacherRepository = teacherRepository;
        this.pwdEncoder = pwdEncoder;
    }

    /* TODO: 서비스는 프레임워크 또는 JPA 에 종속되어서는 안된다. 비즈니스 로직만 포함해야 한다. */
    public TeacherResponseDto addTeacherInfo(TeacherRequestDto teacherRequestDto) {
        Optional<Teacher> teacherOptional = teacherRepository.findByUserName(teacherRequestDto.getUserName());
        teacherOptional.ifPresent(sth ->
                {
                    throw new TeacherException(ErrorCode.DUPLICATE_ERROR, ErrorCode.DUPLICATE_ERROR.getMessage());
                });
        // 이러한 에러를 콘솔에만 찍히지 않고 사용자에게 알려주기 위해 @RestControllerAdvice 와 @ExceptionHandler 를 사용한다.
        Teacher savedTeacher = teacherRepository.save(teacherRequestDto.toEntity(pwdEncoder));

        return Teacher.of(savedTeacher);
    }

    public String promptToken(String userName, String password) {
//        TODO: 아이디가 없을 시 에러로 처리한다.
        Teacher foundTeacher = teacherRepository.findByUserName(userName)
                .orElseThrow(()->new TeacherException(ErrorCode.NOT_FOUND_ERROR, ErrorCode.NOT_FOUND_ERROR.getMessage()));
//        TODO: 비밀번호가 틀렸을 시 에러로 처리한다.
        if (!foundTeacher.getPassword().equals(password)) throw new TeacherException(ErrorCode.PASSWORD_ERROR, ErrorCode.PASSWORD_ERROR.getMessage());
//        TODO: 성공 시 토큰을 발급한다.
        return "";
    }
}
