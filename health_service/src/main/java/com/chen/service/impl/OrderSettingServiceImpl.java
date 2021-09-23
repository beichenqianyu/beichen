package com.chen.service.impl;

import com.chen.dao.OrderSettingDao;
import com.chen.exception.MyException;
import com.chen.pojo.OrderSetting;
import com.chen.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 批量导入预约设置
     * @param osList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doImport(List<OrderSetting> osList) {
        //1.非空判断
        if (!CollectionUtils.isEmpty(osList)){
            //2.遍历
            for (OrderSetting orderSetting : osList) {
                //3.获取导入的设置信息的日期
                Date orderDate = orderSetting.getOrderDate();
                //4.通过日期查询预约设置表
               OrderSetting osInDB = orderSettingDao.findByOrderDate(orderDate);
               //5.如果存在记录
                if (null != osInDB){
                    //5.1 取数据库中的已预约人数
                    int reservations = osInDB.getReservations();
                    //5.2 取出要更新的最大预约数，报错
                    int number = orderSetting.getNumber();
                    //5.3 如果已预约人数 > 要更新的最大预约数，报错
                    if (reservations > number){
                        throw new MyException("最大预约数不能小于预约人数！");
                    }
                    //5.4 否则可以更新最大预约数
                    orderSettingDao.updateNumber(orderSetting);
                }else {
                    //6. 如果不存在，则添加新纪录
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }
}
