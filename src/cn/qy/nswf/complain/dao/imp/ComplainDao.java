package cn.qy.nswf.complain.dao.imp;

import cn.qy.core.dao.imp.BaseDao;
import cn.qy.nswf.complain.dao.IComplainDao;
import cn.qy.nswf.complain.entity.Complain;
import org.hibernate.SQLQuery;

import java.util.List;

/**
 * Created by qy on 2017/6/27.
 */
public class ComplainDao extends BaseDao<Complain> implements IComplainDao {
    @Override
    public List<Object[]> getAnnualStatisticDataByYear(int year) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT imonth, COUNT(comp_id)")
                .append(" FROM tmonth LEFT JOIN complain ON imonth=MONTH(comp_time)")
                .append(" AND YEAR(comp_time)=?")
                .append(" GROUP BY imonth ")
                .append(" ORDER BY imonth");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, year);
        return sqlQuery.list();

    }
}
