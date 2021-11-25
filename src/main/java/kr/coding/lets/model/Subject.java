package kr.coding.lets.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.coding.lets.model.enums.Week;

import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_id;
    @Column(name="name", nullable=false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name="day")
    private Week week;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

}
