package uz.javadev.crudgenericexample.repository.slice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.List;

@Slf4j
public class SliceBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SliceBaseRepository<T, ID> {

    public SliceBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public SliceBaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

    /**
     * A method to retrieve a slice of data based on the given specification and pageable parameters.
     *
     * This method executes a query using the provided specification and pageable details,
     * including sorting and pagination options. It retrieves one additional record to
     * determine if there is a next page of data, creating a `Slice` object to return the result.
     *
     * @param spec     the specification used to filter the data.
     * @param pageable the pagination and sorting information.
     * @return a `Slice` of data, containing the results, pagination details, and whether
     *         there is a next page.
     *
     * @developer javadev
     */
    @Override
    public Slice<T> findAllSliced(Specification<T> spec, Pageable pageable) {
        TypedQuery<T> query = getQuery(spec, pageable.getSort());

        query.setFirstResult((int) pageable.getOffset());
        int extraSize = pageable.getPageSize() + 1;
        query.setMaxResults(extraSize);

        List<T> result = query.getResultList();
        boolean hasNext = result.size() == extraSize;

        if (hasNext) {
            result.remove(extraSize - 1);
        }
        return new SliceImpl<>(result, pageable, hasNext);
    }

}
