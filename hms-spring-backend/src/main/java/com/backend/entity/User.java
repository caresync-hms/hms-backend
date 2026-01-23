package com.backend.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name="userId", column = @Column(name="user_id"))
public class User extends Base {

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(nullable=false,length=100)
    private String firstname;
    
    @Column(nullable=false, length=100)
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Gender gender;
    
    @Column(name="dob",nullable=false)
    private LocalDateTime dob;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @PrePersist
    protected void setDefaults() {
        if (this.role == null) {
            this.role = Role.PATIENT;
        }
        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }
}


