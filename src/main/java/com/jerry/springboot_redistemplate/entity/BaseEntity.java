package com.jerry.springboot_redistemplate.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedAttributeNode;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    protected String id;
    @Column(name = "create_date")
    Date createDate=new Date();
    @Column(name = "update_date")
    Date updateDate;

}
