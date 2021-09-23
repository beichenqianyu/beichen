package com.chen.service;

import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    /**
     * 添加套餐提交
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, List<Integer> checkgroupIds);

    /**
     * 分页查询套餐
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);
}
