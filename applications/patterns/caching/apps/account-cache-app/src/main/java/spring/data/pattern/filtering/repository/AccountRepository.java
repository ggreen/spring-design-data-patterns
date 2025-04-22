package spring.data.pattern.filtering.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import showcase.streaming.event.account.domain.Account;

@Repository
public interface AccountRepository extends KeyValueRepository<Account,String> {
}
