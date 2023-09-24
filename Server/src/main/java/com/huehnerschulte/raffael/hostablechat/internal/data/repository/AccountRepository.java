package com.huehnerschulte.raffael.hostablechat.internal.data.repository;

import com.huehnerschulte.raffael.hostablechat.internal.data.documents.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

    @Query(value = "{'credentials.username':?0}")
    Optional<Account> findByUsername(String username);
}
