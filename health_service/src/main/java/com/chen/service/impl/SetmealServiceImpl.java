package com.chen.service.impl;

import com.chen.dao.SetmealDao;
import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.pojo.Setmeal;
import com.chen.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐提交
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void add(Setmeal setmeal, List<Integer> checkgroupIds) {
        //1.添加套餐
        setmealDao.add(setmeal);
        //2.获取新套餐的id
        Integer setmealId = setmeal.getId();
        //3.非空判断 选中的检查组id集合
        if (null != checkgroupIds){
            //4.遍历添加套餐与检查组的关系
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckGroup(setmealId,checkgroupId);
            }
        }
    }

    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        //使用分页插件来分页，设置分页信息
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //判断是否有条件
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //如果有条件，模糊查询，拼接%%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //查询
        Page<Setmeal> pageInfo = setmealDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<Setmeal>(pageInfo.getTotal(),pageInfo.getResult());
    }
}
