package com.example.dcim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcim.dto.ItemRequest;
import com.example.dcim.dto.ItemResponse;
import com.example.dcim.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	private final ItemService itemService;

	/**
	 * Endpoint for creating a new item.
	 *
	 * @param itemRequest The request payload containing item details.
	 * @return The response entity containing the saved item.
	 */
	@PostMapping
	public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest itemRequest) {
		logger.info("Creating item: {}", itemRequest.getName());
		ItemResponse response = itemService.createItem(itemRequest);
		logger.info("Item created successfully: {}", response);
		return ResponseEntity.ok(response);
	}

}
