package com.hoangbuix.dev.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AbstractDto<E> implements Serializable {
    private Integer id;
    private Integer activeFlag;
    private Date createdDate;
    private Date updatedDate;
//    private List<T> listResult = new ArrayList<>();
}