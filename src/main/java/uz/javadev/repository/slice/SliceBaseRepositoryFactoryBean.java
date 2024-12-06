package uz.javadev.crudgenericexample.repository.slice;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class SliceBaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public SliceBaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @SuppressWarnings({"hiding" })
        protected SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
            SimpleJpaRepository<?, ?> repo = new SliceBaseRepositoryImpl<>(metadata.getDomainType(), entityManager);
            return repo;
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return SliceBaseRepositoryImpl.class;
        }
    }

}
