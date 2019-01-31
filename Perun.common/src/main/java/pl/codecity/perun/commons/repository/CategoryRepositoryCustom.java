package pl.codecity.perun.commons.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.codecity.perun.commons.helper.CategorySearchRequest;
import pl.codecity.perun.commons.model.Category;

public interface CategoryRepositoryCustom {

    void lock(long id);
    Page<Category> search(CategorySearchRequest request);
    Page<Category> search(CategorySearchRequest request, Pageable pageable);
}
