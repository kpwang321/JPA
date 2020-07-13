package cn.itcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author kpwang
 * @create 2020-07-13 17:35
 */
public class JpaUtils {
    private static EntityManagerFactory factory;
    static {
        //1加载配置文件创建工厂对象
        factory = Persistence.createEntityManagerFactory("myJpa");
    }
    //获取EntityManager对象
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
