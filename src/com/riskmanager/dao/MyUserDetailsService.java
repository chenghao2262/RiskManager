package com.riskmanager.dao;

import com.riskmanager.bean.UserBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chenghao on 2016/11/10.
 */

@Repository
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private DataBaseDAO dataBaseDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        UserBean userBean = dataBaseDAO.getUserBeanByName(userName);
        list.add(new SimpleGrantedAuthority(userBean.getRole()));
        User user = new User(userBean.getUsername(),userBean.getPassword(),list);
        return user;
    }
}
