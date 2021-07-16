package com.healme.app.repository.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(length = 36, nullable = false, updatable = false)
    @GenericGenerator(name = "sequence_generator", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence_generator")
    private String id;
}
