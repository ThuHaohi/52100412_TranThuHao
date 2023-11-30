package com.university.utilities;

import java.util.List;

import com.university.entity.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
//@Builder
public class PaginatedResponse<P extends BaseModel> {
	private List<P> elements;
	private long totalElements;
	private int totalPages;
}
