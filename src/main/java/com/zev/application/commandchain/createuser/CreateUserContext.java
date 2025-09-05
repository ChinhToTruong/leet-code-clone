package com.zev.application.commandchain.createuser;

import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.dto.response.user.CreateUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.chain.impl.ContextBase;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserContext extends ContextBase {
    private CreateUserRequest request;
    private List<String> permissions;
    private String role;
    private CreateUserResponse response;
}
