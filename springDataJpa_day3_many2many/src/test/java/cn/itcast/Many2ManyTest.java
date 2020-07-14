package cn.itcast;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kpwang
 * @create 2020-07-14 20:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Many2ManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user=new User();
        user.setUserName("小李");
        Role role=new Role();
        role.setRoleName("java程序员");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeAdd(){
        User user=new User();
        user.setUserName("小李");
        Role role=new Role();
        role.setRoleName("java程序员");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeDelete(){//级联删除
        userDao.delete(1l);
    }
}
