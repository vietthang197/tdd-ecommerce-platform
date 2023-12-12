package com.tdd.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "tesst")
public class Tesst {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "sss")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> sss;

}