package com.shabab.UniversityManagementSystem.security.model;

import com.shabab.UniversityManagementSystem.admin.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "auth_tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}