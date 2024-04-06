package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Boolean existsBy(String Username, String phoneNumber);
    Contact findBy(String username);
}
