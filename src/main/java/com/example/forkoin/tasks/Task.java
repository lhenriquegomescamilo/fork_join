package com.example.forkoin.tasks;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import com.example.forkoin.model.Product;

public class Task extends RecursiveAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;
	private Integer first;
	private Integer last;

	private Double increment;

	public Task(List<Product> products, Integer first, Integer last, Double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (last - first < 10) {
			updatePrices();
		}

	}

	private void updatePrices() {

	}

}
