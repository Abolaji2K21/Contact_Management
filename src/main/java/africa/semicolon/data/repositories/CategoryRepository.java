package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Category;
import africa.semicolon.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByDescription(String description);
    Optional<Category> findByCategoryId(String categoryId);

}
