package com.chen.service;

import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    /**
     * 查询总记录
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 修改检查项提交
     * @param checkItem
     */
    void update(CheckItem checkItem);
}
