package cn.itcast.service;

import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author kpwang
 * @create 2020-07-14 20:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {
    @Autowired
    private CustomerService service;
    @Test
    public void saveCustomer() {
        Customer customer=new Customer();
        customer.setCustName("百度2");
        service.saveCustomer(customer);
    }
}