package com.solmi.shorket.booth.domain;

import com.solmi.shorket.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "BOOTH_INTEREST_TB")
public class BoothInterest {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOTH_TB_IDX", nullable = false)
    @Setter
    private Booth booth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_TB_IDX", nullable = false)
    @Setter
    private User user;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public BoothInterest(User user, Booth booth) {
        this.user = user;
        this.booth = booth;
    }

    public static BoothInterest of(User user, Booth booth) {
        return new BoothInterest(user, booth);
    }

}
