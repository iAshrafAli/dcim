package com.example.dcim.service;

import com.example.dcim.dto.ItemRequest;
import com.example.dcim.dto.ItemResponse;

public interface ItemService {

	ItemResponse createItem(ItemRequest itemRequest); // Save item

}
