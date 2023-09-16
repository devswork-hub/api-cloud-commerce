package com.devworks.cloudcommerce.module.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    /**
     * Base Attributes
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
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resources_actions",
        joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id")
    )
    private Set<Action> actions;

    /**
     * Optional Attributes
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resources_departments",
        joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id")
    )
    private Set<Department> departments;
}
