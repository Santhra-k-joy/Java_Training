package com.epsilon.training.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@ToString
public class Product {
private int id;
private String name;
private String description;
private String brand;
private String category;
private String picture;
private String quantityPerUnit;
private double unitPrice;
private double discount;
}
