package fer.hr.foodapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

@Entity(name="user_table")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String username;

    @Column
    String password;

    @Column
    String role;

    public User() { }

    public User(String username, String password, String role) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.username = username;
        this.password = encoder.encode(password);
        this.role = role;
    }

    public User(int id, String username, String password, String role) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.id = id;
        this.username = username;
        this.password = encoder.encode(password);
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>(Arrays.asList());
        set.add(new SimpleGrantedAuthority(role));
        return set;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if (object.getClass().equals(this.getClass())) {
            User comp = (User) object;
            if ((comp.getId()==id) && (comp.getUsername().equals(username))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return username;
    }
}
