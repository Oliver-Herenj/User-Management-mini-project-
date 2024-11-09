package com.oliverIT.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="user_master")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name="user_name")
	private String uname;

	@Column(name = "email")
	private String email;

	@Column(name = "phno")
	private String phno;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "pwd_updated")
	private String pwdUpdated;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateEntity state;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityEntity city;

	@CreationTimestamp
	@Column(name = "created_date",updatable = false)
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date",insertable = false)
	private LocalDate updatedDate;


}
