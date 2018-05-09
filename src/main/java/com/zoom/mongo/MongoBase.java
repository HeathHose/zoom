package com.zoom.mongo;

import com.zoom.util.GenericsUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

/**
 * Created by liangbo.zhou on 18-5-9.
 */
public class MongoBase<E> implements MongoDataProcess<E> {

    /**
     * 日志对象
     */
    private static final Logger logger = LogManager.getLogger(MongoBase.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Entity的类型
     */
    private Class<E> entityClass;

    public MongoBase() {
        this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    @Override
    public void sortData() {

    }

    @Override
    public List<E> findAll() {
        return this.findAll(null);
    }

    @Override
    public List<E> findAll(String collcetionName) {

        if (StringUtils.isBlank(collcetionName)) {
            collcetionName = mongoTemplate.getCollectionName(GenericsUtils
                    .getSuperClassGenricType(getClass()));
            if (StringUtils.isBlank(collcetionName)) {
                collcetionName = this.entityClass.getSimpleName().toLowerCase();
            }
            logger.info("findAll's param collcetionName is null,so it default is "
                    + collcetionName);
        }
        return mongoTemplate.findAll(entityClass, collcetionName);
    }

    @Override
    public long findCount() {
        long count = 0;
        count = mongoTemplate.count(new Query(), entityClass);
        return count;
    }

    @Override
    public void insert(E e, String collectionName) {

    }

    @Override
    public void insert(E e) {

    }

    @SuppressWarnings("rawtypes")
    private Criteria createCriteria(Map<String, Object> gtMap,
                                    Map<String, Object> ltMap, Map<String, Object> eqMap,
                                    Map<String, Object> gteMap, Map<String, Object> lteMap,
                                    Map<String, String> regexMap, Map<String, Collection> inMap,
                                    Map<String, Object> neMap) {
        Criteria c = new Criteria();
        List<Criteria> listC = new ArrayList<Criteria>();
        Set<String> _set = null;
        if (gtMap != null && gtMap.size() > 0) {
            _set = gtMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).gt(gtMap.get(_s)));
            }
        }
        if (ltMap != null && ltMap.size() > 0) {
            _set = ltMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).lt(ltMap.get(_s)));
            }
        }
        if (eqMap != null && eqMap.size() > 0) {
            _set = eqMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).is(eqMap.get(_s)));
            }
        }
        if (gteMap != null && gteMap.size() > 0) {
            _set = gteMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).gte(gteMap.get(_s)));
            }
        }
        if (lteMap != null && lteMap.size() > 0) {
            _set = lteMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).lte(lteMap.get(_s)));
            }
        }

        if (regexMap != null && regexMap.size() > 0) {
            _set = regexMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).regex(regexMap.get(_s)));
            }
        }

        if (inMap != null && inMap.size() > 0) {
            _set = inMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).in(inMap.get(_s)));
            }
        }
        if (neMap != null && neMap.size() > 0) {
            _set = neMap.keySet();
            for (String _s : _set) {
                listC.add(Criteria.where(_s).ne(neMap.get(_s)));
            }
        }
        if (listC.size() > 0) {
            Criteria[] cs = new Criteria[listC.size()];
            c.andOperator(listC.toArray(cs));
        }
        return c;
    }

    private Criteria createCriteria(Map<String, Object> eqMap) {
        return this.createCriteria(null, null, eqMap, null, null, null, null,
                null);
    }

    private Criteria createCriteria(Map<String, Object> eqMap,
                                    Map<String, Object> neMap) {
        return this.createCriteria(null, null, eqMap, null, null, null, null,
                neMap);
    }

    @Override
    public long findCount(Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> eqMap, Map<String, Object> gteMap, Map<String, Object> lteMap, Map<String, String> regexMap, Map<String, Collection> inMap, Map<String, Object> neMap) {
        long count = 0;
        Criteria c = this.createCriteria(gtMap, ltMap, eqMap, gteMap, lteMap,
                regexMap, inMap, neMap);
        Query query = null;
        if (c == null) {
            query = new Query();
        } else {
            query = new Query(c);
        }
        count = mongoTemplate.count(query, entityClass);
        return count;
    }

    @Override
    public long findCount(Criteria queryC) {
        Query query = new Query(queryC);
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public long findCount(Criteria... orList) {
        long count = 0;
        Criteria c = new Criteria();
        Query query = null;
        if (orList != null && orList.length > 0) {
            c.orOperator(orList);
        }
        query = new Query(c);

        count = mongoTemplate.count(query, entityClass);
        return count;
    }

    @Override
    public long findCount(Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> eqMap, Map<String, String> regexMap, Map<String, Collection> inMap) {
        return this.findCount(gtMap, ltMap, eqMap, null, null, regexMap, inMap,
                null);
    }

    @Override
    public long findCountByContainRegex(Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> eqMap, Map<String, String> regexMap) {
        return this.findCount(gtMap, ltMap, eqMap, regexMap, null);
    }

    @Override
    public List<E> findListByPage(Map<String, Object> eqMap, Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> gteMap, Map<String, Object> lteMap, Map<String, String> regexMap, Map<String, Collection> inMap, Map<String, Object> neMap, List<Sort.Order> orders, int pageIndex, int pageSize) {
        List<E> list = null;
        Criteria c = this.createCriteria(gtMap, ltMap, eqMap, gteMap, lteMap,
                regexMap, inMap, neMap);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
        }
        Query query = null;
        if (c == null) {
            query = new Query();
        } else {
            query = new Query(c);
        }
        if (sort != null) {
            query = query.with(sort);
        }
        if (pageSize > 0) {
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        list = mongoTemplate.find(query, entityClass);

        return list;
    }

    @Override
    public E findObject(Map<String, Object> eqMap, Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> gteMap, Map<String, Object> lteMap, Map<String, String> regexMap, Map<String, Collection> inMap) {
        E e = null;
        List<E> list = this.findList(eqMap, gtMap, ltMap, gteMap, lteMap,
                regexMap, inMap, null, null);
        if (list != null && list.size() > 0) {
            e = list.get(0);
        }
        return e;
    }

    @Override
    public List<E> findList(Criteria... orList) {
        return this.findListByPage(null, 0, 0, orList);
    }

    @Override
    public List<E> findListByOrder(List<Sort.Order> orders, Criteria... orList) {
        return this.findListByPage(orders, 0, 0, orList);
    }

    @Override
    public List<E> findListByPage(Criteria c, List<Sort.Order> orders, int pageIndex, int pageSize) {
        Query query = new Query(c);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
        }
        if (sort != null) {
            query = query.with(sort);
        }
        if (pageSize > 0) {
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<E> findListByOrder(Criteria c, List<Sort.Order> orders) {
        return this.findListByPage(c, orders, 0, 0);
    }

    @Override
    public List<E> findList(Criteria c) {
        return this.findListByPage(c, null, 0, 0);
    }

    @Override
    public E findObject(Criteria c) {
        List<E> list = this.findList(c);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<E> findListByPage(List<Sort.Order> orders, int pageIndex, int pageSize, Criteria... orList) {
        List<E> list = null;
        Criteria c = new Criteria();
        Query query = null;
        if (orList != null && orList.length > 0) {
            c.orOperator(orList);
        }
        query = new Query(c);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
            query = query.with(sort);
        }
        if (pageSize > 0) {
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        list = mongoTemplate.find(query, entityClass);
        return list;
    }

    @Override
    public List<E> findListNotContainOrder(Map<String, Object> eqMap, Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> gteMap, Map<String, Object> lteMap, Map<String, String> regexMap, Map<String, Collection> inMap, Map<String, Object> neMap) {
        return this.findList(eqMap, gtMap, ltMap, gteMap, lteMap, regexMap,
                inMap, neMap, null);
    }

    @Override
    public List<E> findList(Map<String, Object> eqMap, Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> gteMap, Map<String, Object> lteMap, Map<String, String> regexMap, Map<String, Collection> inMap, Map<String, Object> neMap, List<Sort.Order> orders) {
        List<E> list = null;
        Criteria c = this.createCriteria(gtMap, ltMap, eqMap, gteMap, lteMap,
                regexMap, inMap, neMap);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
        }
        Query query = null;
        if (c == null) {
            query = new Query();
        } else {
            query = new Query(c);
        }
        if (sort != null) {
            query = query.with(sort);
        }
        list = mongoTemplate.find(query, entityClass);

        return list;
    }
}
