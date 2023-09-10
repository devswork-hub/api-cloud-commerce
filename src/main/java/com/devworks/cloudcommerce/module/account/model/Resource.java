package com.devworks.cloudcommerce.module.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String link;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module moduleId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resource_permissions",
        joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "resource_permission_id", referencedColumnName = "id")
    )
    private Set<Permission> resourcePermissions = new HashSet<>();

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
