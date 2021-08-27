import M_01.dao.dao.UserDao;
import M_01.dao.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public class TestMyBatis {
    public static void main(String[] args) throws IOException {
        //MyBatis API
        //1.加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.通过sqlSessionFactory构建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.通过SqlSession获得DAO实现类的对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //5.测试查询方法
        User user = userDao.queryUserById(1);
        System.out.println(user);

        //事务提交
        sqlSession.commit();

        //资源释放
        sqlSession.close();


    }
}
