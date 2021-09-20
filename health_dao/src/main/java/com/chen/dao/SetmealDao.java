package com.chen.dao;

import com.chen.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

public interface SetmealDao {

    /**
     * 添加套餐提交
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐与检查组的关系
     * @param setmealId
     * @param checkgroupId
     */
    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);
}
