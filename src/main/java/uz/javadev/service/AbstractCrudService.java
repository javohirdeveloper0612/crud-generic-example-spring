package uz.javadev.service;

import org.springframework.data.domain.Pageable;
import uz.javadev.service.dto.CommonResultData;
import uz.javadev.service.dto.PageableResult;

import java.io.Serializable;
import java.util.List;

public interface AbstractCrudService<DTO, ID extends Serializable> {

    CommonResultData<PageableResult<List<DTO>>> getAll(DTO filter, Pageable pageable);

    CommonResultData<DTO> getById(ID id);

    CommonResultData<DTO> create(DTO dto);

    CommonResultData<DTO> update(ID id, DTO dto);

    CommonResultData<Boolean> deleteById(ID id);

}

