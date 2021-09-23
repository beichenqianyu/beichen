package com.chen.controller;


import com.chen.constant.MessageConstant;
import com.chen.entity.PageResult;
import com.chen.entity.QueryPageBean;
import com.chen.entity.Result;
import com.chen.pojo.Setmeal;
import com.chen.service.SetmealService;
import com.chen.utils.QiNiuUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    //添加日志
    private static final Logger log = LoggerFactory.getLogger(SetmealController.class);

    @Autowired
    private SetmealService setmealService;

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile){
        //1.获取原文件名
        String filename = imgFile.getOriginalFilename();
        //2.获取后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //3.生成唯一文件名
        String imgName = UUID.randomUUID().toString() + suffix;
        //4.调用七牛工具上传文件

        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(),imgName);
            //5.构建map
            Map<String, String> resultMap = new HashMap<String,String>();
            //6 map集合中的两个key ： domain ，imgName
            //返回结果给前端
            /**
             * result{ flag:
             *         message:
             *         data:{
             *         imgName: 唯一文件名
             *         domain: 七牛上bucket的域名 imageurl = domain + imgName
             */
            resultMap.put("domain",QiNiuUtils.DOMAIN);
            resultMap.put("imgName",imgName);
            //返回result
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,resultMap);
        } catch (Exception e) {
            log.error("上传文件失败",e);
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }

    }

    /**
     * 添加套餐提交
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam List<Integer> checkgroupIds){
        //调用业务添加套餐
        setmealService.add(setmeal,checkgroupIds);
        //返回操作的结果
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }


    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
      PageResult<Setmeal> pageResult = setmealService.findPage(queryPageBean);

        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }


}
