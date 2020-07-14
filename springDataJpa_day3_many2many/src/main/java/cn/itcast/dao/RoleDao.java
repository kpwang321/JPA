package cn.itcast.dao;

import cn.itcast.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author kpwang
 * @create 2020-07-14 20:29
 */
public interface RoleDao extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {
}
