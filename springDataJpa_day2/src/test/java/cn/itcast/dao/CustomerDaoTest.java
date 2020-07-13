package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 立即加载
     */
    @Test
    public void findOne(){
        Customer customer = customerDao.findOne(2l);
        //System.out.println(customer);
    }

    /**
     * 查询所有客户
     */
    @Test
    public void findAll(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    /**
     * save:保存或者更新
     * 根据传递的对象是否存在主键id
     * 如果没有id，保存
     * 如果有id 更新
     * */
    @Test
    public void testSave(){
        Customer customer=new Customer();
        customer.setCustName("黑马程序员");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);
    }
    @Test
    public void testUpdate(){
        Customer customer=new Customer();
        customer.setCustId(13l);
        customer.setCustName("黑马程序员");
        //customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customer.setCustAddress("重庆");
        customer.setCustPhone("13372782171");
        customerDao.save(customer);
    }

    /**
     *
     */
    @Test
    public void testDelete(){
        customerDao.delete(12l);
    }

    /**
     * 测试统计查询
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);

    }
    /**
     * 是否存在
     */
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(111l);
        System.out.println(exists);


    }

    /**
     * 根据id从数据库查询
     *      @Transactional:保证getOne正常运行
     * 延迟加载
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(13l);
        //System.out.println(customer);


    }



}