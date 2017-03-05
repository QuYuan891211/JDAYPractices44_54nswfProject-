package Test.Test.Service.Imp;

import Test.Test.Dao.ITestDao;
import Test.Test.Dao.Imp.TestDao;
import Test.Test.Entity.Person;
import Test.Test.Service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by qy on 2017/2/23.
 */
@Service("testService")
public class TestService implements ITestService {
    @Override
    public void Say() {
        System.out.print("say hi");
    }

    @Resource
    private ITestDao testDao;
    @Override
    public void save(Person person) {
        testDao.save(person);
        //int a = 1/0;
    }

    @Override
    public void findPerson(Serializable id) {
       //save(new Person("dada"));
        testDao.find(id);
    }

}
