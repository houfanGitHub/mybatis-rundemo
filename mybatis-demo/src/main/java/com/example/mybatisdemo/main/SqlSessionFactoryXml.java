package com.example.mybatisdemo.main;

import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryXml {

    public static void main(String[] args) {
        InputStream resourceAsStream = null;
        SqlSession sqlSession = null;
        try {
            //1.定位到mybatis-config.xml
            String resource = "mybatis-config.xml";
            //2.并读取装载。获取输入流InputStream。
            resourceAsStream = Resources.getResourceAsStream(resource);
            //3.创建sqlSessionFactory
            //4.解析InputStream mybatis-config.xml配置文件中相关配置项解析，校验，保存起来
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            //5.创建sqlSession，SqlSession中肯定保存了配置文件内容信息和执行数据库相关的操作。
            //可以执行命令、获取映射器和管理事务
            //调用的是DefaultSqlSessionFactory的openSession()
            sqlSession = sqlSessionFactory.openSession();
            //6.获取userMapper对象，但是UserMapper是接口，并且没有实现类。
            // 返回的是Mapper接口的代理对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //7.userMapper接口中的方法是如何关联到SQL的，
            // 这个猜想可能是有个专门映射的类，
            // 另外，肯定使用到了接口全路径名+方法名称，这个才能确保方法和SQL关联
            // （主要是使用的时候，都是方法名必须和SQL中statementId一致，由此猜想的）。
            //8.最后底层使用JDBC去操作数据库
            User user = mapper.findUserById(1L);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
