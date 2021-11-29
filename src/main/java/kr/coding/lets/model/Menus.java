package kr.coding.lets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="menus")
@Getter
@ToString
@NoArgsConstructor
public class Menus {
    @Id
    @Column(name="menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String uri;

    @Builder
    public Menus (String name, String uri){
        this.name = name;
        this.uri = uri;
    }
}
