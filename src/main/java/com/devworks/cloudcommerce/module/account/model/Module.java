package com.devworks.cloudcommerce.module.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private int priority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "modules_resources",
        joinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id")
    )
    private Set<Resource> resources = new HashSet<>();
}
