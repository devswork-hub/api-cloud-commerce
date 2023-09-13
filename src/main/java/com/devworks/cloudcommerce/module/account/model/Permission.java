package com.devworks.cloudcommerce.module.account.model;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.constants.PermissionTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permissions")
public class Permission {
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
    @Enumerated(EnumType.STRING)
    private PermissionTypes name;

    @Column(nullable = false)
    private boolean active;

    /**
     * Defines the name to the permission_type.
     *
     * @param name The name to be defined.
     * @throws BadRequestException If the name is not valid.
     */
    public void setName(String name) {
        try {
            this.name = PermissionTypes.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid role type with name " + name);
        }
    }
}


