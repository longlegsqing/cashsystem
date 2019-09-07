package com.cashsystem.cmd.impl.common;

import com.cashsystem.cmd.Command;
import com.cashsystem.cmd.Commands;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.Subject;
import com.cashsystem.entity.Account;

import java.util.*;

@CommandMeta(
        name = "BZ",
        desc = "帮助信息",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account = subject.getAccount();
        if (account == null){
            entranceHelp();
        }else {
            switch (account.getAccountType()){
                case CUSTOMER:
                    customerHelp();
                    break;
                case ADMIN:
                    adminHelp();
                    break;
                    default:

            }
        }
    }
    //Map.values() 方法，会返回所有的Value集合
    public void entranceHelp(){
        print("欢迎",Commands.ENTERANCE_COMMANDS.values());
    }
    public void customerHelp(){
        print("客户端",Commands.CUSTOMER_COMMANDS.values());
    }
    public void adminHelp(){
        print("管理端",Commands.ADMIN_COMMANDS.values());
    }
    //通用的打印命令
    public void print(String title, Collection<Command> collection){
        System.out.println("****************"+title+"****************");


        Map<String , List<String>> helpInfo = new HashMap<>();

        for (Command command : collection){
            CommandMeta commandMeta = command.getClass().getDeclaredAnnotation(CommandMeta.class);
            String group = commandMeta.group();//key

            List<String> func = helpInfo.computeIfAbsent(group, k -> new ArrayList<>());
            func.add(commandMeta.desc()+"("+commandMeta.name()+")");
        }
        int i = 0;
        for (Map.Entry<String,List<String>> entry : helpInfo.entrySet()){
            i++;
            System.out.println(i+"."+entry.getKey());
            int j = 0;
            for (String item:entry.getValue()) {
                j++;
                System.out.println("\t"+i+"."+j+" "+item);
            }
        printInfo("请输入菜单编号（忽略大小写）");

        }
    }
}
