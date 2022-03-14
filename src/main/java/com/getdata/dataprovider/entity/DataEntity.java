package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class DataEntity {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime createDateTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;
    @OneToOne(mappedBy = "data", cascade = CascadeType.ALL, orphanRemoval = true)
    private BrandEntity brand;
}
