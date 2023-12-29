package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// identityInfoPlaceHolder
@Table(name = "user")
public class User { // implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    // @JsonManagedReference("AdminAirline")
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline adminAirline;

    @JsonIgnore
    @NotNull
    @Column(name = "reset_pass")
    private boolean resetPass;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.toString())); // ADMIN or CLIENT
//    }

//    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @Override
    public boolean isEnabled() {
        return true;
    }
}
