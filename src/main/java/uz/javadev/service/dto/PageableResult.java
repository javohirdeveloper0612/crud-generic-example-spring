package uz.javadev.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageableResult<T> {
    private T content;
    private String totalCount;
}
