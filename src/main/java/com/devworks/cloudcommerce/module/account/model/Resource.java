package com.devworks.cloudcommerce.module.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module moduleId;

    private String link;

    @ManyToMany(fetch = FetchType.EAGER,
        cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
        }
    )
    @JoinTable(name = "role_resources",
        joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "resource_permission_id", referencedColumnName = "id")
    )
    private final Set<ResourcePermission> resourcePermissions = new HashSet<>();

}
