package com.example.dcim.service.impl;

import org.springframework.stereotype.Service;

import com.example.dcim.config.CategoryConfig;
import com.example.dcim.dto.ItemRequest;
import com.example.dcim.dto.ItemResponse;
import com.example.dcim.entity.Item;
import com.example.dcim.exception.InvalidCategoryException;
import com.example.dcim.repository.ItemRepository;
import com.example.dcim.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
	private final CategoryConfig categoryConfig;


	public ItemResponse createItem(ItemRequest itemRequest) {
		// Validate category
		if (!categoryConfig.getAllowedCategories().contains(itemRequest.getCategory())) {
			throw new InvalidCategoryException(
					"Invalid category. Allowed categories: " + categoryConfig.getAllowedCategories());
		}

		// Save item
		Item item = Item.builder().name(itemRequest.getName()).category(itemRequest.getCategory()).build();
		itemRepository.save(item);

		return ItemResponse.builder().id(item.getId()).name(item.getName()).category(item.getCategory())
				.message("Item saved successfully").build();
	}

}
