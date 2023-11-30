package com.university.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseModel {
	
//	@Column(name="customer_id")
//	@Type(type = "uuid-char")
//	private UUID customerId;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderDetail> orderDetails;
	
	@Column(name = "status")
	private String status;
	
	@Transient
	private double totalPrice; 
	
}
