package com.spring.app.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"users"})
@Entity
@Table(name = "roles")
@NamedNativeQuery(
        name = "Role.findByUserId",
        query = "select * from roles r inner join role_users ru on r.role_id = ru.role_id where user_id = ?1",
        resultClass = Role.class
)
public class Role extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "flag", nullable = false)
    @JsonIgnore
    private String flag;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "role_users",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    public void removeUser(User user) {
        if (Objects.nonNull(user)) {
            this.users.remove(user);
        }
    }

    public void addUser(User user) {
        if (Objects.nonNull(user)) {
            this.users.add(user);
        }
    }

    public void removePermission(Permission permission) {
        if (Objects.nonNull(permission)) {
            this.permissions.remove(permission);
        }
    }

    public void addPermission(Permission permission) {
        if (Objects.nonNull(permission)) {
            this.permissions.add(permission);
        }
    }

    public void clearPermission() {
        this.permissions = new ArrayList<>();
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
