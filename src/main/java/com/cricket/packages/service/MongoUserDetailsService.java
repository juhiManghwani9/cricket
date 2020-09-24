package com.cricket.packages.service;

import java.util.Arrays;
import java.util.List;
import com.cricket.packages.persistence.UserDetailData;
import com.cricket.packages.repository.UserDetailDao;
import com.cricket.packages.response.UserDetailResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailDao userDetailDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetailData userDetailData = userDetailDao.getUserDetailByEmailAndVerified(username, true);
            List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
            return new User(userDetailData.getEmailId(), userDetailData.getPassword(), authorities);


    }

    public UserDetailResponse getUserDetail(String username) throws Exception {

        UserDetailData userDetailData = userDetailDao.getUserDetailByEmail(username);
        UserDetailResponse userDetailResponse=new UserDetailResponse();
        BeanUtils.copyProperties(userDetailData,userDetailResponse);
        return userDetailResponse;

    }

}