package com.example.demo.model;

import lombok.Data;

@Data
public class ProductModel {
	private Long proId;
	private String proName;
	private String proPrice;
	private String proDesc;
	private Long catId;
}
