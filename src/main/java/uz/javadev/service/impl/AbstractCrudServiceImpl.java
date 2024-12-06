package uz.javadev.crudgenericexample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import uz.javadev.crudgenericexample.repository.slice.SliceBaseRepository;
import uz.javadev.crudgenericexample.service.AbstractCrudService;
import uz.javadev.crudgenericexample.service.dto.CommonResultData;
import uz.javadev.crudgenericexample.service.dto.PageableResult;
import uz.javadev.crudgenericexample.service.filter.AbstractSpecification;
import uz.javadev.crudgenericexample.service.mapper.EntityMapper;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static uz.javadev.crudgenericexample.utils.PaginationUtil.generateUnPaginationHttpHeaders;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCrudServiceImpl<
        ID extends Serializable,
        DTO,
        ENTITY,
        MAPPER extends EntityMapper<DTO, ENTITY>,
        SPECIFICATION extends AbstractSpecification<ENTITY, DTO>,
        REPOSITORY extends SliceBaseRepository<ENTITY, ID>>
        implements AbstractCrudService<DTO, ID> {

    private final MAPPER mapper;
    private final REPOSITORY repository;
    private final SPECIFICATION specification;
    private final String tableName;

    public AbstractCrudServiceImpl(MAPPER mapper, SPECIFICATION specification, REPOSITORY repository) {
        this.mapper = mapper;
        this.specification = specification;
        this.repository = repository;
        this.tableName = specification.getTableName();
    }

    /**
     * Retrieves a paginated list of entities based on the given filter and pageable parameters.
     *
     * @param filter   the filter criteria for querying entities.
     * @param pageable the pagination and sorting information.
     * @return a `CommonResultData` containing a paginated result with the list of DTOs.
     */
    @Override
    public CommonResultData<PageableResult<List<DTO>>> getAll(DTO filter, Pageable pageable) {
        var allSliced = repository.findAllSliced(specification.createSpecification(filter), pageable);

        if (allSliced == null || allSliced.isEmpty()) {
            log.debug("{}s not found by filter: {}", tableName, filter);
            return CommonResultData.failed(tableName + "s not found !",
                    new PageableResult<List<DTO>>()
                            .setContent(Collections.emptyList())
                            .setTotalCount("0"));
        }

        var resultList = new PageableResult<List<DTO>>()
                .setContent(allSliced.stream().map(mapper::toDto).toList())
                .setTotalCount(generateUnPaginationHttpHeaders(allSliced, pageable));

        log.info("{}s result size: {}", tableName, resultList.getTotalCount());
        return CommonResultData.success(resultList);
    }

    /**
     * Retrieves a specific entity by its ID.
     *
     * @param id the ID of the entity to retrieve.
     * @return a `CommonResultData` containing the DTO of the retrieved entity.
     */
    @Override
    public CommonResultData<DTO> getById(ID id) {
        var entity = repository.findById(id).orElse(null);
        if (entity == null) {
            log.debug("{} not found by id: {}", tableName, id);
            return CommonResultData.failed(tableName + " not found by id: " + id);
        }
        return CommonResultData.success(mapper.toDto(entity));
    }

    /**
     * Creates a new entity with the provided DTO.
     *
     * @param dto the DTO containing the details of the entity to create.
     * @return a `CommonResultData` containing the DTO of the created entity.
     */
    @Override
    public CommonResultData<DTO> create(DTO dto) {
        try {
            var savedEntity = repository.save(mapper.toEntity(dto));
            return CommonResultData.success(mapper.toDto(savedEntity));
        } catch (Exception e) {
            log.error("Create new {} handle exception: {}", tableName, e.getMessage());
            return CommonResultData.failed("Create new " + tableName + " handle exception: " + e.getMessage());
        }
    }

    /**
     * Updates an existing entity with the provided ID and DTO.
     *
     * @param id  the ID of the entity to update.
     * @param dto the DTO containing the updated details of the entity.
     * @return a `CommonResultData` containing the DTO of the updated entity.
     */
    @Override
    public CommonResultData<DTO> update(ID id, DTO dto) {
        try {
            var entity = repository.findById(id).orElse(null);
            if (entity == null) {
                log.debug("{} not found by id: {}", tableName, id);
                return CommonResultData.failed(tableName + " not found by id: " + id);
            }
            var savedEntity = repository.save(mapper.partialUpdate(entity, dto));
            return CommonResultData.success(mapper.toDto(savedEntity));
        } catch (Exception e) {
            log.error("Update {} handle exception: {}", tableName, e.getMessage());
            return CommonResultData.failed("Update " + tableName + " handle exception: " + e.getMessage());
        }
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete.
     * @return a `CommonResultData` indicating whether the deletion was successful.
     */
    @Override
    public CommonResultData<Boolean> deleteById(ID id) {
        try {
            repository.deleteById(id);
            if (repository.existsById(id))
                return CommonResultData.failed("Delete " + tableName + " by id: " + id + " was not deleted");
            return CommonResultData.success("DELETED", true);
        } catch (Exception e) {
            log.error("Delete {} handle exception: {}", tableName, e.getMessage());
            return CommonResultData.failed("Delete " + tableName + " handle exception: " + e.getMessage(), false);
        }
    }
}
