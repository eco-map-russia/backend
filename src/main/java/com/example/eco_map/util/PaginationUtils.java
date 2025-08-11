package com.example.eco_map.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PaginationUtils {
    public Pageable buildPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    public Pageable buildSortedPageable(int page, int size, String direction, String sortField) {
        Sort.Direction sortDirection = "asc".equalsIgnoreCase(direction)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return PageRequest.of(page, size, Sort.by(sortDirection, sortField));
    }
}
