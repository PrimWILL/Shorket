package com.solmi.shorket.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER_TOKEN_TB")
public class UserToken {
    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "USERS_TB_IDX", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String token;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'Y'")
    @Column(nullable = false)
    private TokenStatusType statusType;
}

