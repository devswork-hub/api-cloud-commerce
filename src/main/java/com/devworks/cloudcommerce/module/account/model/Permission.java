package com.devworks.cloudcommerce.module.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permissions")
public class Permission implements Serializable {
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id", referencedColumnName = "id", nullable = false)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", nullable = false)
    private Resource resource;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permissions_modules",
        joinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id")
    )
    private Set<Module> modules;
}
