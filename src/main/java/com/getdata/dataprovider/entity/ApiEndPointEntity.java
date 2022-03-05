package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "api_endpoint")
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApiEndPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String endpoint;
    @ManyToOne(fetch = FetchType.LAZY)
    private ApiResourceEntity apiResource;
}
