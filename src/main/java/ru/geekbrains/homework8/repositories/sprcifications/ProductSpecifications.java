package ru.geekbrains.homework8.repositories.sprcifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.homework8.entities.Product;

import java.util.function.Predicate;

public class ProductSpecifications {

	public static Specification<Product> costGreaterThanOrEqualTo(Integer min) {
		return (Specification<Product>) (root, query, cb)-> cb.greaterThanOrEqualTo(root.get("cost"), min);
	}

	public static Specification<Product> costLessThanOrEqualTo(Integer max) {
		return (Specification<Product>) (root, query, cb)-> cb.lessThanOrEqualTo(root.get("cost"), max);
	}
}
