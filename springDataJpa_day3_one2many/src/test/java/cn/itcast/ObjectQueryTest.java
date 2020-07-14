package cn.itcast;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author kpwang
 * @create 2020-07-13 22:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:applicationContext.xml" )
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    @Test
    @Transactional
    public void testQuery1(){
        Customer customer = customerDao.getOne(4l);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }

        //System.out.println(customer);
    }
    @Test
    @Transactional
    public void testQuery2(){
        Customer customer = customerDao.findOne(4l);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        System.out.println(linkMans.size());

        //System.out.println(customer);
    }
    /*多到一查询*/
    @Test
    @Transactional
    public void testQuery3(){
        LinkMan linkMan = linkManDao.findOne(4l);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);

    }

}