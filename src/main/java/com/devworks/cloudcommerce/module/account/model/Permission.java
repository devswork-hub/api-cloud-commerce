package com.devworks.cloudcommerce.module.account.model;

import com.devworks.cloudcommerce.module.account.constants.PermissionTypes;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resource_permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PermissionTypes name;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resourceId;
}


