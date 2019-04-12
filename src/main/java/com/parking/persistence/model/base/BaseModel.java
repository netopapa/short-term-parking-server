package com.parking.persistence.model.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class BaseModel {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
}
