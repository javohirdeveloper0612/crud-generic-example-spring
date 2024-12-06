package uz.javadev.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import uz.javadev.repository.slice.SliceBaseRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<ENTITY, ID extends Serializable> extends SliceBaseRepository<ENTITY, ID>, JpaSpecificationExecutor<ENTITY> {
}
