package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.Subject;
import com.cashsystem.entity.Goods;

@CommandMeta(
        name = "XJSP",
        desc = "下架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsSoldOutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("下架商品");
        System.out.println("请输入要下架的商品编号");
        int goodsId = scanner.nextInt();
        Goods goods = this.goodsSerive.getGoods(goodsId);

        if (goods == null){
            printInfo("您选择下架商品不存在！");
        }else {
            printInfo("下架商品如下：");
            System.out.println(goods);
            printInfo("确认是否下架：y/n");
            scanner.nextLine();
            String flg = scanner.nextLine();
            if ("y".equalsIgnoreCase(flg)){
                boolean effect = this.goodsSerive.soldOutGoods(goodsId);
                if (effect) {
                    printInfo("商品下架成功");
            } else {
                printInfo("商品下架失败");
            }
            }else {
                printInfo("您取消了下架该商品");
            }

        }

    }
}
