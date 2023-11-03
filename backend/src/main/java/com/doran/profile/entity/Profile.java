package com.doran.profile.entity;

import java.util.List;

import com.doran.animal.entity.Animal;
import com.doran.child.entity.Child;
import com.doran.favorite.entity.Favorite;
import com.doran.letter.entity.Letter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Letter> letters;
}
