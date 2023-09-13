package com.devworks.cloudcommerce.module.account.model;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusTypes;
import com.devworks.cloudcommerce.module.account.constants.PermissionTypes;
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
    private AccountStatusTypes accountStatus;

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

    /**
     * Defines the name to the account_status.
     *
     * @param accountStatus The name to be defined.
     * @throws BadRequestException If the account_status is not valid.
     */
    public void setAccountStatus(String accountStatus) {
        try {
            this.accountStatus = AccountStatusTypes.valueOf(accountStatus);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid account status type with name " + accountStatus);
        }
    }

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
