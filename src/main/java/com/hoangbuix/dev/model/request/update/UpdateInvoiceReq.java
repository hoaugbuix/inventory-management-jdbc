package com.hoangbuix.dev.model.request.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInvoiceReq {
//    @NotNull(message = "Tên invoice trống")
//    @NotEmpty(message = "Tên invoice trống")
//    @Size(min = 1, max = 300, message = "Độ dài tên invoice từ 1 - 300 ký tự")
//    private String name;

    private String code;
    private int type;
    private int qty;
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date toDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fromDate;
    private int productId;
    private int activeFlag;
    private Date createdDate;
    private Date updatedDate;
}
