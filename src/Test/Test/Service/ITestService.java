package Test.Test.Service;

import Test.Test.Entity.Person;

import java.io.Serializable;

/**
 * Created by qy on 2017/2/23.
 */

public interface ITestService {
    void Say();
    void save(Person person);
    void findPerson(Serializable id);
}
