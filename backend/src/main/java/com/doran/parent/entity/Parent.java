package com.doran.parent.entity;

import com.doran.child.entity.Child;
import com.doran.parent.type.Provider;
import com.doran.user.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parent {

	@Id
	@Column(name = "parent_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(length = 11)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private Provider provider;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
