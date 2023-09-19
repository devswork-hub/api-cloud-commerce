package com.devworks.cloudcommerce.module.account.model;

import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_credentials")
public class UserCredentials implements Serializable {
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
     * Required Attributes
     */
    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "password_salt", nullable = false)
    private String passwordSalt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatusType accountStatus;

    /**
     * Optional Attributes
     */
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_credentials_roles",
        joinColumns = @JoinColumn(name = "user_credentials_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }

    public boolean isAccountNonExpired() {return true; }
    public boolean isAccountNonLocked() {return true; }
    public boolean isCredentialsNonExpired() {return true; }
    public boolean isEnabled() {return true; }
}
