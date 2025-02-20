package com.example.dcim.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
	private Long id;
	private String name;
	private String category;
	private String message;
}
