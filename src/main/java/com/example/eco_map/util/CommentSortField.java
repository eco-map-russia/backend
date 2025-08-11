package com.example.eco_map.util;

import lombok.Getter;

@Getter
public enum CommentSortField {
    CREATED_AT("createdAt");
    private final String fieldName;

    CommentSortField(String fieldName) {
        this.fieldName = fieldName;
    }

}
