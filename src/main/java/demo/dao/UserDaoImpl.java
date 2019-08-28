package demo.dao;

import demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    //查询所有用户
    public List<User> findAll() {
       return  mongoTemplate.findAll(User.class);
    }

    @Override
    //登录校验用户
    public List<User> findUserNameAndPassword(String username, String password) {
        Criteria criatira = new Criteria();
        criatira.andOperator(Criteria.where("username").is(username), Criteria.where("password").is(password));
        List<User> users = mongoTemplate.find(new Query(criatira), User.class);
        return users;
    }

    @Override
    //注册保存用户
    public void register(String username, String password) {
          User user =new User(username,password);
          mongoTemplate.save(user,"users");
        System.out.println("mongoDB插入数据成功,集合为col,文档为："+mongoTemplate.getCollection("users"));
        List<User> list = mongoTemplate.findAll(User.class, "users");
        System.out.println("mongoDB查询数据成功,集合为users,文档为：");
        for (User user1 : list) {
            System.out.println(user1.getUsername()+";"+user1.getPassword());
        }


    }

    @Override
    public Boolean checkUsernsme(String username) {
        Query query =new Query(Criteria.where("username").is(username));
        List<User> users = mongoTemplate.find(query, User.class);
        if(users.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
