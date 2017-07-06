package cn.qy.core.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qy on 2017/5/2.
 */
public class QueryHelper {

    //from 子句
    private String fromClause = "";
    //where 子句
    private String whereClause = "";
    //order by 子句
    private String orderByClause = "";

    //where 子句中?对应的参数值
    private List<Object> parameters;

    public static String ORDER_BY_ASC = "ASC";//升序
    public static String ORDER_BY_DESC = "DESC";//降序
    private String alias = "";
    /**
     * 利用构造方法构造from子句
     * @param clazz 实体类
     * @param alias 别名
     */
    public QueryHelper(Class clazz,String alias){
        this.alias = alias;
        fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
    }
    /**
     * 组装where子句
     * @param condition 条件
     * @param params 条件中?对应的参数值
     * @return QueryHelper
     */
    public QueryHelper addCondition(String condition, Object...params){
        if(whereClause.length() > 0){
            whereClause += " AND " + alias + "." + condition;
        } else {
            whereClause = " WHERE " + alias + "." + condition;
        }
        if(parameters == null){
            parameters = new ArrayList<Object>();
        }
        for(Object param: params){
            parameters.add(param);
        }
        return this;
    }
    /**
     * 构建order by 子句
     * @param property 排序的属性
     * @param order 升序(asc)或是降序(desc)
     * @return QueryHelper
     */
    public QueryHelper addOrderByProperty(String property, String order){
        if(orderByClause.length() > 0){
            orderByClause += "," + alias + "." + property + " " + order;
        } else {
            orderByClause += " ORDER BY " + alias + "." + property + " " + order;
        }
        return this;
    }

    //返回列表查询hql语句
    public String getListQueryHql(){
        return fromClause + whereClause + orderByClause;
    }

    //返回查询总记录数的hql
    public String getCountHql(){
        return "SELECT Count(*) " + fromClause + whereClause;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}

