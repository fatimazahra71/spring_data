package com.master4.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email", nullable = false, unique = true)
    @Email(message = "entrer une adresse valide")
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name="modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Article> articlesList;

    public User(long id) {
        this.id=id;
    }
}
