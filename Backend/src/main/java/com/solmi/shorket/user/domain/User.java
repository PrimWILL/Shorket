package com.solmi.shorket.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USERS_TB")
public class User {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String email;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String password;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String profileUrl;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private LoginType loginType;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'I'")
    @Column(nullable = false)
    private RoleType userRole;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL",
            nullable = false)
    private Date updatedAt;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'Y'")
    @Column(nullable = false)
    private StatusType statusType;
}

