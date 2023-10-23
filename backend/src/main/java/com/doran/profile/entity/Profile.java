package com.doran.profile.entity;

import com.doran.animal.entity.Animal;
import com.doran.child.entity.Child;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	private String imgUrl;

	@ManyToOne
	@JoinColumn(name = "child_id")
	private Child child;

	@OneToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
}
