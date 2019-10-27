package com.jerry.springboot_redistemplate.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User extends BaseEntity {
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "age")
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
