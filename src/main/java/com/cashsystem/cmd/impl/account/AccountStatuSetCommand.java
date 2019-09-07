package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.Subject;
@CommandMeta(
        name = "QTZH",
        desc = "启停账号",
        group = "账号信息"
)
public class AccountStatuSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}
