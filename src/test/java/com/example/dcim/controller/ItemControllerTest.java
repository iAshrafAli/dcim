package com.example.dcim.controller;

import com.example.dcim.dto.ItemRequest;
import com.example.dcim.dto.ItemResponse;
import com.example.dcim.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

	@Mock
	private ItemService itemService;

	@InjectMocks
	private ItemController itemController;

	@Test
	void testCreateItemSuccess() {
		ItemRequest itemRequest = new ItemRequest("Laptop", "A");
		ItemResponse itemResponse = new ItemResponse(1L, "Laptop", "A", "Item saved successfully");

		when(itemService.createItem(itemRequest)).thenReturn(itemResponse);

		ResponseEntity<ItemResponse> response = itemController.createItem(itemRequest);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(itemResponse, response.getBody());
	}

}
