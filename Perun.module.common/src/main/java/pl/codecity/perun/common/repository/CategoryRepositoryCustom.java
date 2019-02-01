package pl.codecity.perun.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.codecity.perun.common.helper.CategorySearchRequest;
import pl.codecity.perun.common.model.Category;

public interface CategoryRepositoryCustom {

    void lock(long id);
    Page<Category> search(CategorySearchRequest request);
    Page<Category> search(CategorySearchRequest request, Pageable pageable);
}
