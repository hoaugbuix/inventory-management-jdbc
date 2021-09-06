package com.hoangbuix.dev.model.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleReq {
    @NotNull(message = "Tên category trống")
    @NotEmpty(message = "Tên category trống")
    @Size(min = 1, max = 300, message = "Độ dài tên category từ 1 - 300 ký tự")
    private String roleName;

    private String description;

    private int activeFlag;
}
