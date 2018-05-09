package com.zoom.redis;

import com.zoom.core.BaseDataProcess;
import org.springframework.data.redis.core.ListOperations;

import java.util.List;
import java.util.Set;

/**
 * Created by liangbo.zhou on 18-5-9.
 */
public interface RedisDataProcess extends BaseDataProcess {
    /**
     * 缓存value操作
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheValue(String k, String v, long time);

    /**
     * 缓存value操作
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheValue(String k, String v);

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    boolean containsValueKey(String k);

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    boolean containsSetKey(String k);

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    boolean containsListKey(String k);

    boolean containsKey(String key);

    /**
     * 获取缓存
     *
     * @param k
     * @return
     */
    String getValue(String k);

    /**
     * 移除缓存
     *
     * @param k
     * @return
     */
    boolean removeValue(String k);

    boolean removeSet(String k);

    boolean removeList(String k);

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 缓存set操作
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheSet(String k, String v, long time);

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheSet(String k, String v);

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheSet(String k, Set<String> v, long time);

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheSet(String k, Set<String> v);

    /**
     * 获取缓存set数据
     *
     * @param k
     * @return
     */
    Set<String> getSet(String k);

    /**
     * list缓存
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheList(String k, String v, long time);

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheList(String k, String v);

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheList(String k, List<String> v, long time);

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheList(String k, List<String> v);

    /**
     * 获取list缓存
     *
     * @param k
     * @param start
     * @param end
     * @return
     */
    List<String> getList(String k, long start, long end);

    /**
     * 获取总条数, 可用于分页
     *
     * @param k
     * @return
     */
    long getListSize(String k);

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps
     * @param k
     * @return
     */
    long getListSize(ListOperations<String, String> listOps, String k);

    /**
     * 移除list缓存
     *
     * @param k
     * @return
     */
    boolean removeOneOfList(String k);
}
