package com.chen.service;

import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {

    /**
     * 检查组分页
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, List<Integer> checkitemIds);

    /**
     * 通过id查询检查组信息
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 根据检查组id查询选中的检查项信息集合
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 更新检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, List<Integer> checkitemIds);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
