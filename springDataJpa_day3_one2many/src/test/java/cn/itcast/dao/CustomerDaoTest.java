package cn.itcast.dao;

import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author kpwang
 * @create 2020-07-13 22:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:applicationContext.xml" )
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    /**
     * 保存一个客户，保存一个联系人
     * */
    @Test
    @Transactional//配置事务
    @Rollback(false)
    public void testAdd(){
        Customer customer=new Customer();
        customer.setCustName("百度");
        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李");
        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    /**
     * 保存一个客户，保存一个联系人
     * */
    @Test
    @Transactional//配置事务
    @Rollback(false)
    public void testAdd1(){
        Customer customer=new Customer();
        customer.setCustName("百度");
        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李");
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    @Test
    @Transactional//配置事务
    @Rollback(false)
    public void testAdd2(){
        Customer customer=new Customer();
        customer.setCustName("百度");
        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李");
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    //级联添加：保存一个可以的同时，保存客户的所有联系人（需要在操作主体的实体类上配置casacde属性）
    @Test
    /*@Transactional//配置事务
    @Rollback(false)*/
    public void testCascadeAdd(){
        Customer customer=new Customer();
        customer.setCustName("百度2");
        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李2");
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
    }
    //级联删除：
    @Test
    @Transactional//配置事务
    @Rollback(false)
    public void testCascadeDelete(){
        customerDao.delete(2l);
    }



}