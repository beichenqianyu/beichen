package com.chen.jobs;

import com.chen.dao.SetmealDao;
import com.chen.utils.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClearImgJob {

    private static final Logger log = LoggerFactory.getLogger(ClearImgJob.class);

    @Autowired
    private SetmealDao setmealDao;

    /**
     * 清理7牛图片的方法
     */
    public void clean7NiuImgJob(){

        log.info("开始执行清理七牛上的垃圾图片。。。");
        //1.查询七牛的图片，得到一个集合
        List<String> picsIn7Niu = QiNiuUtils.listFile();
        log.debug("七牛上有的图片。。",null != picsIn7Niu?picsIn7Niu.size():0);
        //2.查询数据库中所有套餐的图片，集合
        List<String> picsInDb = setmealDao.selectImgs();
        log.debug("数据库上有的图片。。。",null != picsInDb?picsInDb.size():0);
        //3.七牛减去数据库的= 垃圾的
        picsIn7Niu.removeAll(picsInDb);
        //需要删除的图片 .toArray(new String[]{})转成数组
        String[] picsNeed2Delete = picsIn7Niu.toArray(new String[]{});
        log.debug("要删除的图片共有张。。",picsNeed2Delete.length);
        //4.调用七牛工具删除垃圾图片
        QiNiuUtils.removeFiles(picsNeed2Delete);
        log.info("完成清理七牛上的垃圾图片");


    }


}
