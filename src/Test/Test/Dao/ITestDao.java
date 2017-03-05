package Test.Test.Dao;

import Test.Test.Entity.Person;

import java.io.Serializable;

/**
 * Created by qy on 2017/2/27.
 */
public interface ITestDao {
    void save(Person person);
    void find(Serializable id);
}
