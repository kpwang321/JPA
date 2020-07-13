package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author kpwang
 * @create 2020-07-13 18:13
 */
public class JpqlTest {

    /**
     * 查询全部
     *     jpql：from Customer  针对的是对象
     *     sql:select * from cst_customer  表
     */

    @Test
    public void testFindAll(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //----------
        String jpql=" from Customer ";
        Query query = entityManager.createQuery(jpql);
        //发送查询并封装结果集
        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        //---------

        transaction.commit();
        entityManager.close();
    }
    /**
     * 倒序查询全部
     *     jpql：from Customer order by custId desc 针对的是对象
     *     sql:select * from cst_customer order by cust_id desc 表
     */
    @Test
    public void testFindAlldesc(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //----------
        String jpql=" from Customer order by custId desc ";
        Query query = entityManager.createQuery(jpql);
        //发送查询并封装结果集
        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }



        TypedQuery<Customer> query1 = entityManager.createQuery(jpql, Customer.class);
        List<Customer> resultList1 = query1.getResultList();
        for (Customer customer : resultList1) {
            System.out.println(customer);
        }

        //---------

        transaction.commit();
        entityManager.close();
    }
    /**
     * 统计客户的总人数
     *     sql: select count(1) from cst_customer
     *     jpql: select count(1) from Customer
     * */
    @Test
    public void testCount(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //----------
        String jpql=" select count(1) from Customer";
        Query query = entityManager.createQuery(jpql);
        //发送查询并封装结果集
        long count= (Long)query.getSingleResult();
        System.out.println(count);
        //---------

        transaction.commit();
        entityManager.close();
    }
    /**
     * 分页查询
     *     sql: select * from cst_customer limit ?,?
     *     jpql: from Customer
     * */
    @Test
    public void testPaged(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //----------
        String jpql=" from Customer ";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        //对参数赋值
        query.setFirstResult(1);
        query.setMaxResults(5);
        //---------
        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManager.close();
    }
    /**
     * 条件查询查询
     *     sql: select * from cst_customer where cust_name list '%传智播客%'
     *     jpql: from Customer where custName like ?
     * */
    @Test
    public void testCondition(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //----------
        String jpql=" from Customer where custName like ?";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        //对参数赋值
        query.setParameter(1,"%传智播客%");
        //---------
        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManager.close();
    }
}
