package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author kpwang
 * @create 2020-07-13 16:46
 */
public class JpaTest {
    //测试jpa的保存
    //jpa操作步骤
    //   1加载配置文件创建工厂对象
    //   2通过实体管理类工厂获取实体管理器
    //   3获取事务对象，开启事务
    //   4完成增删改查操作
    //   5提交事务(回滚事务)
    //   6释放资源
    @Test
    public void testSave(){
        //1加载配置文件创建工厂对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2通过实体管理类工厂获取实体管理器
        EntityManager entityManager = factory.createEntityManager();
        //3获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction();//获取事务对象
        transaction.begin();
        //4完成增删改查操作
        Customer customer=new Customer();
        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");
        //保存
        entityManager.persist(customer);//保存操作
        //5提交事务(回滚事务)
        transaction.commit();
        entityManager.close();
        factory.close();
    }
    @Test
    public void testJpaUtils(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        //3获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction();//获取事务对象
        transaction.begin();
        //4完成增删改查操作
        Customer customer=new Customer();
        customer.setCustName("传智播客1");
        customer.setCustIndustry("教育1");
        //保存
        entityManager.persist(customer);//保存操作
        //5提交事务(回滚事务)
        transaction.commit();
        entityManager.close();
    }
    //根据id查询客户
    @Test
    public void find(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 1l);//立即加载
        //System.out.println(customer);
        transaction.commit();
        entityManager.close();

    }
    //根据id查询客户
    @Test
    public void getReference(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.getReference(Customer.class, 1l);//延迟加载
        //System.out.println(customer);
        transaction.commit();
        entityManager.close();

    }
    //根据id删除客户
    @Test
    public void getRemove(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 1l);
        entityManager.remove(customer);
        //System.out.println(customer);
        transaction.commit();
        entityManager.close();

    }
    //更新客户操作
    @Test
    public void getUpdate(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 2l);
        customer.setCustAddress("重庆");
        customer.setCustLevel("1");
        customer.setCustIndustry("it教育");
        Customer merge = entityManager.merge(customer);
        System.out.println(merge);
        //System.out.println(customer);
        transaction.commit();
        entityManager.close();

    }
}
