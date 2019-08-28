package demo.test;

import demo.dao.UserDao;
import demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class Test {
    @Autowired
    private UserDao userDao;

    public void test1(){
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
}
