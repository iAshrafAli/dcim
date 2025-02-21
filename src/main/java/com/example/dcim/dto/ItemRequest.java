package com.example.dcim.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRequest {

	@NotBlank(message = "Name cannot be blank")
	private String name;

	@NotBlank(message = "Category cannot be blank")
	private String category;

}
