package ru.geekbrains.homework8.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework8.entities.Product;
import ru.geekbrains.homework8.exceptions.ResourceNotFoundException;
import ru.geekbrains.homework8.services.ProductService;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
@Validated
public class ProductController {

	private final ProductService service;

	@GetMapping
	public Page<Product> index(
			@RequestParam(defaultValue = "0") @PositiveOrZero() Integer min,
			@RequestParam(defaultValue = Integer.MAX_VALUE + "") @PositiveOrZero() Integer max,
			@RequestParam(defaultValue = "1") Integer page) {
		if (page<1) page=1;
		return service.findAll(min, max, page);
	}

	@PostMapping( consumes = "application/json", produces = "application/json")
	public Product save(@RequestBody Product product) {
		return service.save(product);
	}

	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") Long id) {
		return service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product Not Found id: "+ id ));

	}

	@GetMapping("/delete/{id}")
	public Product delete(@PathVariable("id") long id) {
		return service.deleteById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Unable delete Product by id: "+ id ));

	}


}
