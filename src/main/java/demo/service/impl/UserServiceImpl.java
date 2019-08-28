package demo.service.impl;

import demo.dao.UserDao;
import demo.dao.UserDaoImpl;
import demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoImpl userDaoImpl;


    @Override
    public List<User> findAll() {
        return userDaoImpl.findAll();
    }

    @Override
    public List<User> findUserNameAndPassword(String username, String password) {
        return userDaoImpl.findUserNameAndPassword(username,password);
    }

    @Override
    public void register(String username, String password) {
        userDaoImpl.register(username, password);
        System.out.println("用户注册成功");


    }

    @Override
    public Boolean checkUsernsme(String username) {
        return userDaoImpl.checkUsernsme(username);
    }
}
