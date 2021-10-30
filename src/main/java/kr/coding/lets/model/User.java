package kr.coding.lets.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false)
    private String phone;
    private boolean enabled;
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
        name="user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Roles> roles = new HashSet<>();

    @Builder
    public User(String name, String email, String picture, String phone){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
    }

    public User update(String name, String picture, String phone){
        this.name = name;
        this.picture = picture;
        this.phone = phone;

        return this;
    }
    
    
}
