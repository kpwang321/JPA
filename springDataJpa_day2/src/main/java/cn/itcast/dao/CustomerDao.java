package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-07-13 22:45
 */
public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {
    /**
     * 根据客户名称查询客户
     *
     * */
    @Query("from Customer where custName=?")
    List<Customer> findJpql(String custName);

    @Query("from Customer where custName=?2 and custAddress=?1")
    List<Customer> findCustNameAddress(String name,String address);

    @Query("update Customer set custName=?2 where custId=?1")
    @Modifying//声明更新操作
    void updateNameById(Long id,String custName);

    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Customer> findSql();

    @Query(value = "select * from cst_customer where cust_name like ?",nativeQuery = true)
    List<Customer> findCondition(String name);
    @Query(value = "select * from cst_customer where cust_name =:#{#customer.custName}",nativeQuery = true)
    List<Customer> findCondition1(@Param("customer") Customer customer);


    //方法名命名    不需要注解  findBy+属性名+And/Or
    List<Customer> findByCustName(String name);

    List<Customer> findByCustNameAndCustAddress(String name,String address);





}
