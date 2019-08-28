package demo.service;

import demo.domain.User;
import org.springframework.stereotype.Service;


import java.util.List;


public interface UserService {
    //查询所有用户
    public List<User> findAll();
    //登录校验用户
    public List<User> findUserNameAndPassword(String username,String password);
     //注册保存用户
    public void register(String username,String password);
    //校验用户名
    public Boolean checkUsernsme(String username);



}
