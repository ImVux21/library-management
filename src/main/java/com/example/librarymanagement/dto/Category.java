package com.example.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Category {
    LANGUAGE("Ngôn ngữ"),
    PHILOSOPHY("Triết học"),
    SCIENCE("Khoa học"),
    TECHNOLOGY("Công nghệ"),
    ARTS("Mỹ thuật"),
    HISTORY("Lịch sử"),
    LITERATURE("Văn học");

    String name;
}
