package kr.coding.lets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private String phone;

    @Builder
    public User(String name, String email, String picture, Role role, String phone){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.phone = phone;
    }

    public User update(String name, String picture, String phone){
        this.name = name;
        this.picture = picture;
        this.phone = phone;

        return this;
    }
    
    public String getRoleKey(){
        return this.role.getKey();
    }
}
