package com.zoom.mongo;

import com.zoom.core.BaseDataProcess;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by liangbo.zhou on 18-4-9.
 */
interface MongoDataProcess<E> extends BaseDataProcess {

    /**
     * Title: findAll
     * Description: 查询表格所有的数据
     * return List<E> 当前实体对应的表格的所有数据。
     */
    List<E> findAll();

    /**
     * Title: findAll
     * Description: 查询表格所有的数据
     * param clazz
     * 该表所对应的实体
     * param collcetionName
     * 表格名称
     * return List<E>
     */
    List<E> findAll(String collcetionName);

    /**
     * Title: findCount
     * Description:查询当前实体对应表格的所有数据的条数
     * return long 总条数
     * throws
     */
    long findCount();

    /***
     *
     *  Title: insert
     *  Description: 入库
     *  param e
     *            实体数据
     *  param collectionName
     *            表名
     */
    void insert(E e, String collectionName);

    /**
     * Title: insert
     * Description: 入库
     * param e
     * 实体数据
     */
    void insert(E e);

    /**
     * Title: findCount
     * Description: 根据各种条件查询总数
     * param gtMap
     * param ltMap
     * param eqMap
     * param gteMap
     * param lteMap
     * param regexMap
     * param inMap
     * param neMap
     * return long 总数
     * throws
     */
    @SuppressWarnings("rawtypes")
    long findCount(Map<String, Object> gtMap, Map<String, Object> ltMap,
                   Map<String, Object> eqMap, Map<String, Object> gteMap,
                   Map<String, Object> lteMap, Map<String, String> regexMap,
                   Map<String, Collection> inMap, Map<String, Object> neMap);

    /***
     *
     *  Title: findCount
     *  Description: 根据创建的条件查询总数
     *  param  queryC
     *  return long    返回类型
     *  throws
     */
    long findCount(Criteria queryC);

    /**
     * Title: findCount
     * Description: 根据多个种条件 or 的方式查询
     * param orList
     * or的查询条件的集合
     * return long
     * throws
     */
    long findCount(Criteria... orList);

    @SuppressWarnings("rawtypes")
    long findCount(Map<String, Object> gtMap, Map<String, Object> ltMap,
                   Map<String, Object> eqMap, Map<String, String> regexMap,
                   Map<String, Collection> inMap);

    long findCountByContainRegex(Map<String, Object> gtMap,
                                 Map<String, Object> ltMap, Map<String, Object> eqMap,
                                 Map<String, String> regexMap);

    /**
     * Title: findListByPage
     * Description: 根据分页+条件获取对应的实体集合
     * param eqMap
     * param gtMap
     * param ltMap
     * param gteMap
     * param lteMap
     * param regexMap
     * param inMap
     * param orders
     * 排序集合
     * param pageIndex
     * 页码
     * param pageSize
     * 每页条数
     * return List<E> 实体集合
     * throws
     */
    @SuppressWarnings("rawtypes")
    List<E> findListByPage(Map<String, Object> eqMap,
                           Map<String, Object> gtMap, Map<String, Object> ltMap,
                           Map<String, Object> gteMap, Map<String, Object> lteMap,
                           Map<String, String> regexMap, Map<String, Collection> inMap,
                           Map<String, Object> neMap, List<Sort.Order> orders, int pageIndex,
                           int pageSize);

    /**
     * Title: findOneObject
     * Description: 符合条件的某一条数据
     * param eqMap
     * param gtMap
     * param ltMap
     * param gteMap
     * param lteMap
     * param regexMap
     * param inMap
     * return E 返回该数据对应的实体
     */
    @SuppressWarnings("rawtypes")
    E findObject(Map<String, Object> eqMap, Map<String, Object> gtMap,
                 Map<String, Object> ltMap, Map<String, Object> gteMap,
                 Map<String, Object> lteMap, Map<String, String> regexMap,
                 Map<String, Collection> inMap);

    /**
     * Title: findList
     * Description: 多个查询条件or方式组合查询
     * param orList
     * return List<E>
     * throws
     */
    List<E> findList(Criteria... orList);

    /**
     * Title: findListByOrder
     * Description: 多个查询条件or方式组合查询
     * param orList
     * param orders
     * return List<E>
     * throws
     */
    List<E> findListByOrder(List<Sort.Order> orders, Criteria... orList);

    /**
     * Title: findListByPage
     * Description: 根据查询条件直接查询
     * param  c
     * param  orders
     * param  pageIndex
     * param  pageSize
     * return List<E>
     * throws
     */
    List<E> findListByPage(Criteria c, List<Sort.Order> orders, int pageIndex,
                           int pageSize);


    List<E> findListByOrder(Criteria c, List<Sort.Order> orders);

    List<E> findList(Criteria c);

    /**
     * Title: findObject
     * Description: 根据查询条件查询某一个object
     * param  c
     * return E
     * throws
     */
    E findObject(Criteria c);

    /**
     * Title: findListByPage
     * Description: 多个查询条件or方式组合查询
     * param orList
     * or的查询条件的集合
     * param orders 排序规则
     * param pageIndex
     * 第几页
     *
     * @param pageSize 每页多少条
     *                 return List<E> 符合条件的集合
     *                 throws
     */
    List<E> findListByPage(List<Sort.Order> orders, int pageIndex,
                           int pageSize, Criteria... orList);

    @SuppressWarnings("rawtypes")
    List<E> findListNotContainOrder(Map<String, Object> eqMap,
                                    Map<String, Object> gtMap, Map<String, Object> ltMap,
                                    Map<String, Object> gteMap, Map<String, Object> lteMap,
                                    Map<String, String> regexMap, Map<String, Collection> inMap,
                                    Map<String, Object> neMap);


    @SuppressWarnings("rawtypes")
    List<E> findList(Map<String, Object> eqMap,
                     Map<String, Object> gtMap, Map<String, Object> ltMap,
                     Map<String, Object> gteMap, Map<String, Object> lteMap,
                     Map<String, String> regexMap, Map<String, Collection> inMap,
                     Map<String, Object> neMap, List<Sort.Order> orders);

}
