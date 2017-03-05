package Test.Test;

import Test.Test.Entity.Person;
import Test.Test.Service.ITestService;
import Test.Test.Service.Imp.TestService;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Created by qy on 2017/2/23.
 */
public class TestMerge {
/*    private ClassPathXmlApplicationContext ctx;
    @Before
    public void loadCtx(){
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Test
    public void test(){
        loadCtx();
        ITestService ts = (ITestService) ctx.getBean("testService");
        ts.Say();
    }
    @Test
    public void testHibernate(){
        loadCtx();
        SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();

        session.save(new Person("Tina"));

        ts.commit();
        session.close();

    }
    @Test
    public void TestServiceAndDao(){
        loadCtx();
        ITestService testService = (ITestService) ctx.getBean("testService");
        testService.save(new Person("Caoxi"));
    }
    @Test
    public void TestAdvicefind(){
        loadCtx();
        ITestService testService = (ITestService) ctx.getBean("testService");
        testService.findPerson("sdasdawafa");
    }
    @Test
    public void TestRollback(){
        loadCtx();
        ITestService testService = (ITestService) ctx.getBean("testService");
        testService.save(new Person("Rollback"));
    }*/
}
