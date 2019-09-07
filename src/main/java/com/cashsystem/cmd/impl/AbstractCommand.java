package com.cashsystem.cmd.impl;

import com.cashsystem.cmd.Command;
import com.cashsystem.service.AccountSerive;
import com.cashsystem.service.GoodsSerive;
import com.cashsystem.service.OrderService;

public abstract class AbstractCommand implements Command {
    //启动所有服务
    public AccountSerive accountSerive;
    public GoodsSerive goodsSerive;
    public OrderService orderService;
    public AbstractCommand(){
        this.accountSerive = new AccountSerive();
        this.goodsSerive = new GoodsSerive();
        this.orderService = new OrderService();
    }
    public  void  printInfo(String info){
        System.out.println(info);
    }


}
