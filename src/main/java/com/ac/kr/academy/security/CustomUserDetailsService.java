package com.ac.kr.academy.security;

import com.ac.kr.academy.domain.user.User;
import com.ac.kr.academy.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 다른 사용자(student, professor, staff, advisor) 매퍼 작성 후 수정 예정
 * */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    //username <- email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(username + "사용자를 찾을 수 없습니다.");
        }

        return new CustomUserDetails(user);
    }
}
