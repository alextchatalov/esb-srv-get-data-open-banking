package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "api_resource")
@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApiResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String apiFamilyType;
    private String apiVersion;
    @OneToMany(mappedBy = "apiResource", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<ApiEndPointEntity> apiEndpoint = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private ParticipantEntity participant;
}
