package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "securitybase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer securityBaseID;

    @Column(nullable = false, length = 64)
    private byte[] passwordHash;

    @Column(nullable = false, length = 16)
    private byte[] salt;

    private LocalDateTime lastLogInDate;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isActive = true;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isLocked = false;

    private Integer loginAttempts = 0;

    private LocalDateTime lastPasswordChange;

    private LocalDateTime registrationDate;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;
}
