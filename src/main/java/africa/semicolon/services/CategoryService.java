package africa.semicolon.services;


import africa.semicolon.data.models.Category;
import africa.semicolon.data.models.Contact;
import africa.semicolon.dtos.requests.CreateCategoryRequest;
import africa.semicolon.dtos.requests.DeleteCategoryRequest;
import africa.semicolon.dtos.requests.EditCategoryRequest;
import africa.semicolon.dtos.response.CreateCategoryResponse;
import africa.semicolon.dtos.response.DeleteCategoryResponse;
import africa.semicolon.dtos.response.EditCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    EditCategoryResponse editCategory(EditCategoryRequest editCategoryRequest);

    DeleteCategoryResponse deleteCategory(DeleteCategoryRequest deleteCategoryRequest);

    List<Category> getAllCategories();

    List<Contact> getContactByCategoryDescription(String description);

    void addContactToCategory(String username,String categoryId, Contact contact);

    void removeNoteFromCategory(String username,String categoryId, Contact contact);
}
