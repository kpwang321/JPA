package cn.itcast.dao;

import cn.itcast.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author kpwang
 * @create 2020-07-14 20:28
 */
public interface UserDao extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
}
