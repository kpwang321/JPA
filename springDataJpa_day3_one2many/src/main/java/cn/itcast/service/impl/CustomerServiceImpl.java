package cn.itcast.service.impl;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kpwang
 * @create 2020-07-14 20:07
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDao customerDao;
    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }
}
