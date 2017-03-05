package Test.Test.Dao.Imp;

import Test.Test.Dao.ITestDao;
import Test.Test.Entity.Person;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

/**
 * Created by qy on 2017/2/27.
 */
public class TestDao extends HibernateDaoSupport implements ITestDao {

    @Override
    public void save(Person person) {
        getHibernateTemplate().save(person);
    }

    @Override
    public void find(Serializable id) {
        getHibernateTemplate().get(Person.class, id);
    }

}
