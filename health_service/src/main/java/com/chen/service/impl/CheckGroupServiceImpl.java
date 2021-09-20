package com.chen.service.impl;

import com.chen.dao.CheckGroupDao;
import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.pojo.CheckGroup;
import com.chen.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 分页检查组
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {

        //使用分页插件来分页，设置分页信息
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //判断是否有条件
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //如果有条件，模糊查询，拼接%%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //查询
        Page<CheckGroup> pageInfo = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        //返回结果
        return new PageResult<CheckGroup>(pageInfo.getTotal(),pageInfo.getResult());
    }

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void add(CheckGroup checkGroup, List<Integer> checkitemIds) {
        //1.添加检查组信息
        checkGroupDao.add(checkGroup);
        //2.获取新增的检查id
        Integer checkGroupId = checkGroup.getId();
        //3.非空判断
        if (null != checkitemIds){
            //遍历选中的检查项id数组
            for (Integer checkitemId : checkitemIds) {
                //4.添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
    }

    /**
     *通过id查询检查组信息
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 通过检查组id查询选中的检查项id集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 更新检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void update(CheckGroup checkGroup, List<Integer> checkitemIds) {

        //1.更新检查组信息
        checkGroupDao.update(checkGroup);
        //2.获取检查组id
        Integer checkGroupId = checkGroup.getId();
        //3.先删除旧关系
        checkGroupDao.deleteCheckGroupCheckItem(checkGroupId);
        //4.遍历选中的检查项id数组，空判断
        if (null != checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                //5.添加检查组与检查项的新关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }


    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
