package com.account.mx.dto;

public class OwnerDto {

	private Integer idOwner;
	private Long owner;

	public OwnerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OwnerDto(Integer idOwner, Long owner) {
		super();
		this.idOwner = idOwner;
		this.owner = owner;
	}

	public Integer getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(Integer idOwner) {
		this.idOwner = idOwner;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "OwnerDto [idOwner=" + idOwner + ", owner=" + owner + "]";
	}

}
