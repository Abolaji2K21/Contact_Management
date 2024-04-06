package africa.semicolon.services;

import africa.semicolon.contactException.BigContactException;
import africa.semicolon.data.models.Category;
import africa.semicolon.data.models.Contact;
import africa.semicolon.data.repositories.CategoryRepository;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dtos.requests.CreateCategoryRequest;
import africa.semicolon.dtos.requests.DeleteCategoryRequest;
import africa.semicolon.dtos.requests.EditCategoryRequest;
import africa.semicolon.dtos.response.CreateCategoryResponse;
import africa.semicolon.dtos.response.DeleteCategoryResponse;
import africa.semicolon.dtos.response.EditCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserService userService;


    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        return null;
    }

    @Override
    public EditCategoryResponse editCategory(EditCategoryRequest editCategoryRequest) {
        return null;
    }

    @Override
    public DeleteCategoryResponse deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public List<Contact> getContactByCategoryDescription(String description) {
        Category category = getCategoryByDescription(description);
        return category.getContacts();
    }

    @Override
    public void addContactToCategory(String username, String categoryId, Contact contact) {

    }

    @Override
    public void removeNoteFromCategory(String username, String categoryId, Contact contact) {

    }
    private void validateUser(String username) {
        if (!userService.isUserRegistered(username)) {
            throw new BigContactException("User with username " + username + " is not registered");
        }

        if (!userService.isUserLoggedIn(username)) {
            throw new BigContactException("User with username " + username + " is not logged in");
        }
    }
    private Category getCategoryByDescription(String description) {
        Optional<Category> optionalCategory = categoryRepository.findByDescription(description);
        return optionalCategory.orElseThrow(() -> new BigContactException("Category not found with description: " + description));
    }


}
