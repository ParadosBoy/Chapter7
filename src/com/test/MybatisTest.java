package com.test;

import com.po.Customer;
import com.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import utils.MybatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 10574
 */
public class MybatisTest {
    @Test
    public void findCustomerByIdTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Customer customer = sqlSession.selectOne("com.mapper" + ".CustomerMapper.findCustomerById", 2);
        System.out.println(customer.toString());
        sqlSession.close();
    }

    @Test
    public void findCustomerByNameTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Customer> customers = sqlSession.selectList("com.mapper" + ".CustomerMapper.findCustomerByName", "j");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void addCustomerTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Customer customer = new Customer();
        customer.setUsername("rose");
        customer.setJobs("student");
        customer.setPhone("1333333333092");
        int rows = sqlSession.insert("com.mapper" + ".CustomerMapper.addCustomer", customer);
        if (rows > 0) {
            System.out.println("成功插入了" + rows + "条数据!");
        } else {
            System.out.println("插入失败!");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateCustomerTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Customer customer = new Customer();
        customer.setId(4);
        customer.setUsername("rose");
        customer.setJobs("programmer");
        customer.setPhone("133333331111");
        int rows = sqlSession.insert("com.mapper" + ".CustomerMapper.updateCustomer", customer);
        if (rows > 0) {
            System.out.println("成功修改了" + rows + "条数据!");
        } else {
            System.out.println("修改失败!");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteCustomerTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows = sqlSession.insert("com.mapper" + ".CustomerMapper.deleteCustomer", 4);
        if (rows > 0) {
            System.out.println("成功删除了" + rows + "条数据!");
        } else {
            System.out.println("删除失败!");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findAllUserTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        List<User> list = sqlSession.selectList("com.mapper.UserMapper.findAllUser");
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}


