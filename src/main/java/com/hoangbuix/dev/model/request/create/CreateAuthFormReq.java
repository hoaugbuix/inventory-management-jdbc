package com.hoangbuix.dev.model.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthFormReq {
    private int roleId;
    private int menuId;
    private int permission;
}
