package ru.staticvoid.sreeter.repos;

import org.springframework.data.repository.CrudRepository;
import ru.staticvoid.sreeter.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
