package com.chen.service;

import com.chen.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    /**
     * 添加套餐提交
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, List<Integer> checkgroupIds);
}
