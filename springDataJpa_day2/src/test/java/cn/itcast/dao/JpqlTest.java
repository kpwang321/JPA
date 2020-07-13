package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author kpwang
 * @create 2020-07-13 22:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:applicationContext.xml" )
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL(){
        List<Customer> list = customerDao.findJpql("传智播客1");
        System.out.println(list);
    }
    @Test
    public void testfindCustNameAddress(){
        //List<Customer> list = customerDao.findCustNameAddress("传智播客1", "重庆");
        List<Customer> list = customerDao.findCustNameAddress("重庆", "传智播客1");
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }

    /**
     * springDataJpa中使用jpql完成更新/删除操作
     *      需要手动添加事务
     */
    @Test
    @Transactional
    @Rollback(false)//设置是否回滚，默认为回滚
    public void testUpdate(){
        //List<Customer> list = customerDao.findCustNameAddress("传智播客1", "重庆");
        List<Customer> list = customerDao.findCustNameAddress("重庆", "传智播客1");
        for (Customer customer : list) {
            System.out.println(customer);
        }
        customerDao.updateNameById(list.get(0).getCustId(),"黑马");

    }
    @Test
    public void testFindSql(){
        List<Customer> list = customerDao.findSql();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testFindCondition(){
        List<Customer> list = customerDao.findCondition("%传智播客%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testFindCondition1(){
        Customer customer=new Customer();
        customer.setCustName("传智播客1");
        List<Customer> list = customerDao.findCondition1(customer);
        for (Customer customer1 : list) {
            System.out.println(customer1);
        }
    }


}