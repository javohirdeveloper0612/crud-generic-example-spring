package uz.javadev.repository.slice;

import jakarta.annotation.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface SliceBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    Slice<T> findAllSliced(@Nullable Specification<T> spec, Pageable pageable);
}
