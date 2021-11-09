package kr.coding.lets.model;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@NoArgsConstructor
@Table(name="user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String picture;
    @Column(nullable = false, unique=true)
    private String phone;
    private boolean enabled;
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
        name="user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Roles> roles = new HashSet<>();
    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public User(String name, String email, String picture, String phone, Set<Roles>roles){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
    }

    public User update(String email, String picture){
        // this.name = name;
        this.email = email;
        this.picture = picture;
        // this.phone = phone;

        return this;
    }
}
