package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Boolean existsByPhoneNumber(String phoneNumber);
    Contact findContactByContactIdAndUserId(String contactId, String userId);
    List<Contact> findAllByUserIdAndCategory(String userId, String category);
    List<Contact> findAllByUsername(String username);
    Optional<Contact> findByUserId(String userId);
    Optional<Contact> findByCategory(String category);


}
