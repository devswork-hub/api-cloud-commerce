package com.devworks.cloudcommerce.module.account.model;

import com.devworks.cloudcommerce.module.account.constants.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {
    /**
     * Internal Base Attributes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Required Base Attributes
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    /**
     * Optional Attributes
     */
    private String cpf;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_country_code")
    private String phoneCountryCode;

    @Column(name = "phone_code_area")
    private String phoneCodeArea;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @PrePersist
    public void insertDateTime() {
        this.createdAt = LocalDateTime.now();
    }
}
