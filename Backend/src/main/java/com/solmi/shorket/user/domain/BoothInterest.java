package com.solmi.shorket.user.domain;

import com.solmi.shorket.booth.domain.Booth;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * How to create BoothInterest Object?
 * - BoothInterest.createBoothInterest(User user, Booth booth)
 */

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOOTH_INTEREST_TB")
public class BoothInterest {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOTH_TB_IDX", nullable = false)
    private Booth booth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_TB_IDX", nullable = false)
    private User user;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public static BoothInterest createBoothInterest(User user, Booth booth) {
        BoothInterest boothInterest = new BoothInterest();
        boothInterest.setUser(user);
        boothInterest.setBooth(booth);
        return boothInterest;
    }

    private void setBooth(Booth booth) {
        this.booth = booth;
        booth.getInterests().add(this);
    }

    private void setUser(User user) {
        this.user = user;
//        user.getBoothInterests().add(this);
    }
}
