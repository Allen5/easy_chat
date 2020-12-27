package com.allen.easyChat.server.service;

import com.allen.easyChat.server.mapper.UserMapper;
import com.allen.easyChat.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据手机号和密码查找用户
     * @param mobile
     * @param password
     * @return
     */
    public User find(final String mobile, final String password) {
        if ( StringUtils.isEmpty(mobile) ) {
            System.out.println("mobile is empty!");
            return null;
        }
        if ( StringUtils.isEmpty(password) ) {
            System.out.println("password is empty!");
            return null;
        }

        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo(User.MOBILE, mobile)
                .andEqualTo(User.PASSWORD, password);

        List<User> users = userMapper.selectByExample(example);
        if ( CollectionUtils.isEmpty(users) ) {
            System.out.println("user not found with modile: " + mobile + " and password: " + password);
            return null;
        }
        if ( users.size() != 1 ) {
            System.out.println("user duplicated with modile: " + mobile + " and password: " + password);
            return null;
        }
        return users.get(0);
    }

}
