package com.devworks.cloudcommerce.module.account.model;

import com.devworks.cloudcommerce.module.account.constants.AccountStatusTypes;
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
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String email;

    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "password_salt", nullable = false)
    private String passwordSalt;

    @Enumerated(EnumType.STRING)
    private AccountStatusTypes accountStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_credentials_roles",
        joinColumns = @JoinColumn(name = "user_credentials_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
        }
        return authorities;
    }

    public boolean isAccountNonExpired() {return true; }
    public boolean isAccountNonLocked() {return true; }
    public boolean isCredentialsNonExpired() {return true; }
    public boolean isEnabled() {return true; }
}
