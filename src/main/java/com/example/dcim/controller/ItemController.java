package com.example.dcim.controller;

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

	private final ItemService itemService;

	 /**
     * Endpoint for creating a new item.
     *
     * @param itemRequest The request payload containing item details.
     * @return The response entity containing the saved item.
     */
	@PostMapping
	public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest itemRequest) {
		return ResponseEntity.ok(itemService.createItem(itemRequest));
	}

}
