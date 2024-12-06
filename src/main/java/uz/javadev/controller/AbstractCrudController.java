package uz.javadev.crudgenericexample.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.javadev.crudgenericexample.service.AbstractCrudService;
import uz.javadev.crudgenericexample.service.dto.CommonResultData;
import uz.javadev.crudgenericexample.service.dto.PageableResult;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract CRUD Controller providing basic REST API operations for managing entities.
 *
 * @param <ID>      the type of the entity's ID.
 * @param <DTO>     the type of the Data Transfer Object (DTO) representing the entity.
 * @param <SERVICE> the type of the service implementing CRUD operations.
 *
 * @developer javadev
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCrudController<ID extends Serializable, DTO, SERVICE extends AbstractCrudService<DTO, ID>> {

    private final SERVICE service;
    private final String tableName;

    /**
     * Retrieves a paginated list of entities based on the given filter and pageable parameters.
     *
     * @param filter   the filter criteria for querying entities.
     * @param pageable the pagination and sorting information.
     * @return a `CommonResultData` containing a paginated result with the list of DTOs.
     */
    @GetMapping
    public CommonResultData<PageableResult<List<DTO>>> getAll(DTO filter, Pageable pageable) {
        log.info("REST request to getAll {}s by filter: {}", tableName, filter);
        return service.getAll(filter, pageable);
    }

    /**
     * Retrieves a specific entity by its ID.
     *
     * @param id the ID of the entity to retrieve.
     * @return a `CommonResultData` containing the DTO of the retrieved entity.
     */
    @GetMapping("/{id}")
    public CommonResultData<DTO> getById(@PathVariable ID id) {
        log.info("REST request to get {} by id: {} ", tableName, id);
        var resultData = service.getById(id);
        log.info("Get {} by id resultData: {}", tableName, resultData);
        return resultData;
    }

    /**
     * Creates a new entity with the provided DTO.
     *
     * @param requestDTO the DTO containing the details of the entity to create.
     * @return a `CommonResultData` containing the DTO of the created entity.
     */
    @PostMapping
    public CommonResultData<DTO> create(@RequestBody @Valid DTO requestDTO) {
        log.info("REST request to create {} by requestDTO: {}", tableName, requestDTO);
        var resultData = service.create(requestDTO);
        log.info("Create new {} process resultData: {}", tableName, resultData);
        return resultData;
    }

    /**
     * Updates an existing entity with the provided ID and DTO.
     *
     * @param id         the ID of the entity to update.
     * @param requestDTO the DTO containing the updated details of the entity.
     * @return a `CommonResultData` containing the DTO of the updated entity.
     */
    @PutMapping("/{id}")
    public CommonResultData<DTO> update(@PathVariable ID id, @RequestBody @Valid DTO requestDTO) {
        log.info("REST request to update {} by requestDTO: {}", tableName, requestDTO);
        var resultData = service.update(id, requestDTO);
        log.info("Update {} method process resultData: {}", tableName, resultData);
        return resultData;
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete.
     * @return a `CommonResultData` indicating whether the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public CommonResultData<Boolean> delete(@PathVariable ID id) {
        log.info("REST request to delete {} by id: {}", tableName, id);
        var resultData = service.deleteById(id);
        log.info("Delete {} resultData: {}", tableName, resultData);
        return resultData;
    }

}
