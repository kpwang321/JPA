package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    @Test
    public void testSpec(){
        Specification<Customer> specification=new Specification<Customer>() {
            /**
             *
             * @param root  获取需要查询的对象属性
             * @param criteriaQuery
             * @param criteriaBuilder  构造查询条件的，内部封装了很多查询条件(模糊匹配，精准匹配)
             * @return
             * 根据客户名称查询，查询用户名为“传智播客”的客户
             *       查询条件
             *           1查询方式
             *               criteriaBuilder对象
             *           2比较的属性名称
             *               root对象
             */
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1获取比较的属性
                Path<Object> custName = root.get("custName");
                //2构造查询条件
                //第一个参数：需要比较的属性
                //第二个参数：当前需要比较的取值
                Predicate predicate = criteriaBuilder.equal(custName, "传智播客");//进行精准匹配(比较的属性的取值)
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     *     案例：根据客户名和客户地址查询
     */
    @Test
    public void testSpec1() {
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");
                Predicate p1 = criteriaBuilder.equal(custName, "传智播客1");
                Predicate p2 = criteriaBuilder.equal(custAddress, "重庆");
                //多个查询条件组合在一起：组合(1：and关系     2:or关系)
                Predicate predicate = criteriaBuilder.and(p1, p2);//and的形式拼接多个条件
                //Predicate predicate =criteriaBuilder.or(p1,p2);//or的形式拼接多个条件
                return predicate;
            }
        };
        List<Customer> list = customerDao.findAll(specification);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    /**
     * 根据客户名称的模糊匹配
     * */
    @Test
    public void testSpec3(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate like = criteriaBuilder.like(custName.as(String.class), "%传智播客%");
                return like;
            }
        };
        List<Customer> list = customerDao.findAll(specification);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testSpec4(){
        Specification<Customer> specification=new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path custName = root.get("custName");
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "%传智播客%");
                return predicate;
            }
        };
        //第一个参数：排序的顺序
        //第二个参数：排序的属性名
        Sort sort=new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(specification, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testSpec5(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "%智播客%");

                return predicate;
            }
        };
        Pageable pageable=new Pageable() {
            public int getPageNumber() {
                return 1;
            }

            public int getPageSize() {
                return 4;
            }

            public int getOffset() {
                return 0;
            }

            public Sort getSort() {
                return new Sort(Sort.Direction.DESC,"custId");
            }

            public Pageable next() {
                return null;
            }

            public Pageable previousOrFirst() {
                return null;
            }

            public Pageable first() {
                return null;
            }

            public boolean hasPrevious() {
                return false;
            }
        };
        Page<Customer> page = customerDao.findAll(specification, pageable);
        for (Customer customer : page) {
            System.out.println(customer);
        }
    }
    @Test
    public void testSpec6(){
        Specification<Customer> specification=new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path custName = root.get("custName");
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "%智播客%");
                return predicate;
            }
        };
        Pageable pageable=new PageRequest(1,3);
        Page<Customer> page = customerDao.findAll(specification, pageable);
        System.out.println(page.getContent());//得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数

        System.out.println(page.getTotalPages());//总页数
        for (Customer customer : page) {
            System.out.println(customer);
        }

    }

}