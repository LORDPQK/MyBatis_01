package M_01.dao.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *1.加载配置
 * 2.创建SqlSessionFactory
 * 3.创建session
 * 4.事务管理
 * 5.Mapper获取
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    //创建ThreadLocal绑定当前线程中的SqlSession对象
    private static final ThreadLocal<SqlSession> tl= new ThreadLocal<SqlSession>();
    static{//加载配置文件，并构建session工厂
        //1.加载配置文件
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession(){
        SqlSession sqlSession = tl.get();
        if(sqlSession==null){
            sqlSession= sqlSessionFactory.openSession();
            tl.set(sqlSession);
        }
        return sqlSession;

    }

    public static void closeSession(){
        SqlSession sqlSession = tl.get();
        sqlSession.close();
    }

    public static void commit(){
        SqlSession session = openSession();
        session.commit();
        closeSession();
    }

    public static void rollback(){
        SqlSession session = openSession();
        session.rollback();
        closeSession();
    }

    //获取Dao实现类 类型不确定设为泛型 返回dao实现对象
    public static <T> T getmapper(Class<T> mapper){
        SqlSession session = openSession();
        return session.getMapper(mapper);
    }
}
