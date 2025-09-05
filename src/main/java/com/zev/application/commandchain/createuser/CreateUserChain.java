package com.zev.application.commandchain.createuser;

import com.zev.application.commandchain.createuser.command.CheckPermissionCmd;
import com.zev.application.commandchain.createuser.command.CheckRoleCmd;
import com.zev.application.commandchain.createuser.command.CheckUserCmd;
import com.zev.application.commandchain.createuser.command.SaveNewUserCmd;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserChain extends ChainBase {

    @Autowired
    public CreateUserChain(
            CheckPermissionCmd checkPermissionCmd,
            CheckRoleCmd checkRoleCmd,
            CheckUserCmd checkUserCmd,
            SaveNewUserCmd saveNewUserCmd
    ) {
        addCommand(checkPermissionCmd);
        addCommand(checkRoleCmd);
        addCommand(checkUserCmd);
        addCommand(saveNewUserCmd);
    }
}
