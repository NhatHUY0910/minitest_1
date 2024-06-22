package com.minitest_1.repository;

import com.minitest_1.model.Category;
import com.minitest_1.model.dto.CategoryBookCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value =
            "select c.name as category, " +
            "count(b.id) as bookNumber " +
            "from category c " +
            "left join book b on c.id = b.category_id " +
            "group by c.id, c.name " +
            "order by c.name")
    List<CategoryBookCount> getCategoryBookCounts();
}
