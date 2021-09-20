package com.chen.service.impl;

import com.chen.dao.CheckItemDao;
import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.exception.MyException;
import com.chen.pojo.CheckItem;
import com.chen.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查询总记录
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 新增检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //使用分页插件来分页，设置分页信息 页码和每页大小
        //把页码与大小放进本线程
        Page<CheckItem> threadLocalPage = PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //判断是否有条件
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            //如果有条件，模糊查询 拼接 %%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //查询
        Page<CheckItem> pageInfo = checkItemDao.findByCondition(queryPageBean.getQueryString());

        //总记录数
        long total = pageInfo.getTotal();
        //分页结果集
        List<CheckItem> list = pageInfo.getResult();

        //构建pageresult
        PageResult<CheckItem> pageResult = new PageResult<>(total,list);
        //返回给controller
        return pageResult;
    }

    /**
     * 删除检查项
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //1.通过检查项id查询这个id是否被检查组使用了
        //2.统计这个检查项id被检查组使用的次数
        int count = checkItemDao.findCountByCheckItemId(id);
        //3.计数>0被使用，报错，该检查项被检查组使用了，不能删除
        if (count>0){
            throw new MyException("该检查项被检查组使用了，不能删除");
        }
        //4.计数=0，没被使用，可以直接删除
        checkItemDao.deleteById(id);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {

        return checkItemDao.findById(id);
    }

    /**
     * 修改
     * @param checkItem
     */
    @Override
    public void update(CheckItem checkItem) {

        checkItemDao.update(checkItem);
    }


}
