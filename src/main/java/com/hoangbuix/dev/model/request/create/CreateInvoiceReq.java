package com.hoangbuix.dev.model.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceReq {
//    @NotNull(message = "Tên invoice trống")
//    @NotEmpty(message = "Tên invoice trống")
//    @Size(min = 1, max = 300, message = "Độ dài tên invoice từ 1 - 300 ký tự")
//    private String name;

    private String code;

    private int type;
    private String ProductCode;
    private int qty;
    private BigDecimal price;
    private Date toDate;
    private Date fromDate;

    private int activeFlag;
    private Date createdDate;
    private Date updatedDate;
}
