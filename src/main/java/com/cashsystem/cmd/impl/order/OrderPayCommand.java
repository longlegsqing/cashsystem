package com.cashsystem.cmd.impl.order;

import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.Subject;
import com.cashsystem.common.OrderStatus;
import com.cashsystem.entity.Goods;
import com.cashsystem.entity.Order;
import com.cashsystem.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CommandMeta(
        name = "ZFDD",
        desc = "支付订单",
        group = "订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("请输入购买货物的Id+数量，格式：1-8,2-5");
        String string = scanner.nextLine();
        String[] strings = string.split(",");
        List<Goods> goodsList = new ArrayList<>();
        for (String goodsString : strings) {
            String[] str = goodsString.split("-");
            Goods goods = this.goodsSerive.getGoods(Integer.parseInt(str[0]));
            goods.setBuyGoodsNum(Integer.parseInt(str[1]));
            goodsList.add(goods);
        }

        Order order = new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_id(subject.getAccount().getId());
        order.setAccount_name(subject.getAccount().getName());
        order.setCreate_time(LocalDateTime.now());
        int totalMoney = 0;
        int actualMoney = 0;
        //计算订单所花的钱
        for (Goods goods:goodsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsIntroduce(goods.getIntroduce());
            orderItem.setGoodsNum(goods.getBuyGoodsNum());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPrice());
            orderItem.setGoodsDiscount(goods.getDiscount());
            order.orderItemList.add(orderItem);

            int currentMoney = goods.getPrice()*goods.getBuyGoodsNum();
            totalMoney += currentMoney;
            actualMoney = totalMoney*goods.getDiscount()/100;
        }
        order.setTotal_money(totalMoney);
        order.setActual_amount(actualMoney);
        order.setOrder_status(OrderStatus.PLAYING);


        //展示当前订单明细
        System.out.println(order);
        printInfo("请输入是否支付订单：y/n");

        String confirm = scanner.nextLine();
        if ("y".equalsIgnoreCase(confirm)){
            order.setFinish_time(LocalDateTime.now());
            order.setOrder_status(OrderStatus.OK);

            boolean effect = this.orderService.commitOrder(order);
            if (effect){//说明插入订单和订单项成功
                for (Goods goods : goodsList) {
                    boolean isUpdate = this.goodsSerive.updateAfterPay(goods,goods.getBuyGoodsNum());
                    if (isUpdate){
                        System.out.println("库存更新成功");
                    }else {
                        System.out.println("库存更新失败");
                    }
                }
            }
        }else{
            printInfo("订单支付失败");
        }
    }
}
