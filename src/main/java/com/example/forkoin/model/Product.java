package com.example.forkoin.model;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Double price;

	public Product(String string, int i) {
	}

	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public static <T, O, R> Supplier<R> bind(BiFunction<T, O, R> functionCreateNewProduct, T t, O o) {
		return () -> functionCreateNewProduct.apply(t, o);
	}

}
