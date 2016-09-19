package com.example.forkoin.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.forkoin.model.Product;

public class ProductListGenerator {
    private ProductListGenerator() {

    }

    public List<Product> generate(int size) {
        return Stream.generate(Product.bind(Product::new, "C", Math.random()))
                .limit(size).
                        collect(Collectors.toList());
    }

    public static ProductListGenerator newInstance() {
        return new ProductListGenerator();
    }
}
