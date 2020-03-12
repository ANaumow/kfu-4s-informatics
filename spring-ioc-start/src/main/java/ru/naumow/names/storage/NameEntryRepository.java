package ru.naumow.names.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.names.model.NameEntry;

@Repository
public interface NameEntryRepository extends CrudRepository<NameEntry, Long> {
}
