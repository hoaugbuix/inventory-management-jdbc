package com.hoangbuix.dev.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty(notes = "The database generated ID")
    @Column(name = "id")
    private Integer id;

    @Column(name = "active_flag", columnDefinition = "Integer default 1")
    private int activeFlag;

    @Column(name = "created_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
}
