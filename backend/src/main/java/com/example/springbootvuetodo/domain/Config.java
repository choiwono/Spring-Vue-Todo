package com.example.springbootvuetodo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="config")
@Builder
@AllArgsConstructor
public class Config {
    @Id
    private Long id;
    // 필요한 세팅..
}
