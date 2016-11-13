package com.riskmanager.dao;

import com.riskmanager.bean.UserBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * Created by chenghao on 2016/11/13.
 */


@Repository
public class WebContext {

    @Resource
    private DataBaseDAO dataBaseDAO;

    public String getUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername(); }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName(); }
        return String.valueOf(principal);
    }

    public int getCurrentUid(){
        UserBean userBean = dataBaseDAO.getUserBeanByName(getUserName());
        return userBean.getId();
    }
}
