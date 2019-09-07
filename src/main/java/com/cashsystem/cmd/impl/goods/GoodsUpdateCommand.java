package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.Subject;
import com.cashsystem.entity.Goods;

@CommandMeta(
        name = "GXSP",
        desc = "商品更新",
        group = "商品信息"
)
@AdminCommand
public class GoodsUpdateCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("更新商品");
        System.out.println("请输入更新商品的编号");
        int goodsId = scanner.nextInt();
        Goods goods = this.goodsSerive.getGoods(goodsId);

        if (goods == null){
            printInfo("没有此编号的货物");
            return;//结束函数
        }else {
            printInfo("需要更新的商品信息如下：");
            System.out.println(goods);
        }
        printInfo("请输入商品更新后的简介");
        String introduce = scanner.nextLine();
        scanner.nextLine();
        printInfo("请输入商品更新后的库存");
        int stock = scanner.nextInt();
        scanner.nextLine();
        // int stock = Integer.parseInt(scanner.nextLine());
        printInfo("请输入商品更新后的简介单位：包，个，盒......");
        String unit = scanner.nextLine();
        printInfo("请输入商品更新后的价格(元)：保留两位小数");
        int price = new Double(100*scanner.nextDouble()).intValue();
        printInfo("请输入商品更新后的折扣：80代表八折");
        int discount = scanner.nextInt();
        scanner.nextLine();
        printInfo("请确认是否更新： y/n");
        String flg = scanner.nextLine();
        if ("y".equalsIgnoreCase(flg)){
            goods.setIntroduce(introduce);
            goods.setStock(stock);
            goods.setUnit(unit);
            goods.setPrice(price);
            goods.setDiscount(discount);
            //更新数据库表：goods
            boolean effect = this.goodsSerive.modifyGoods(goods);
            if (effect){
                printInfo("商品更新成功");
            }else {
                printInfo("商品更新失败");
            }
        }else {
            printInfo("商品更新取消");
        }

    }
}
