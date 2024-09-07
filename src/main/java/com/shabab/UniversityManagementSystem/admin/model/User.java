package com.shabab.UniversityManagementSystem.admin.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shabab.UniversityManagementSystem.academy.model.Course;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.accounting.Account;
import com.shabab.UniversityManagementSystem.security.model.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "ad_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Cell number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(name = "cell", length = 11, nullable = false, unique = true)
    private String cell;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Token token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    /*Optional*/

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Size(max = 10, message = "Max 10 Characters")
    @Column(name = "gender", length = 10)
    private String gender;

    @Size(max = 255, message = "Maximum 255 Characters")
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Temporal(TemporalType.DATE)
    @Column(name = "joining_date")
    private Date joiningDate;

    public User(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @Transient
    private String username;

    @JsonIgnore
    @Transient
    private String password;

    public enum Role {
        ROLE_ADMIN,
        ROLE_TEACHER,
        ROLE_STAFF
    }

    /*overridden*/

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public String getIdString() {
        return String.valueOf(id);
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

