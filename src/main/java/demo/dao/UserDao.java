package demo.dao;

import demo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao {
      //查询所有用户
    List<User> findAll();

       //校验用户
    public List<User> findUserNameAndPassword(String username,String password);
      //注册
    public void register(String username,String password);
    // 校验用户名
    public Boolean checkUsernsme(String username);

}
