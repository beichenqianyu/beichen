package com.chen.controller;

import com.chen.constant.MessageConstant;
import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.entity.Result;
import com.chen.pojo.CheckItem;
import com.chen.service.CheckItemService;
import com.sun.org.apache.xalan.internal.xsltc.runtime.ErrorMessages_ca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;

    /**
     * 查询总记录
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> checkItemList = checkItemService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemList);
    }

    /**
     * 新增检查项
     * @param checkItem
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        //调用业务添加
        checkItemService.add(checkItem);
        //返回结果
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    /**
     * 删除检查项
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable int id){
        checkItemService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable int id){

        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS,checkItem);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id,@RequestBody CheckItem checkItem){

        checkItem.setId(id);
        checkItemService.update(checkItem);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }


}
