package in.timesinternet.punjiup.repository;

import in.timesinternet.punjiup.entity.FundManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundManagerRepository extends JpaRepository<FundManager, Integer> {
    Optional<FundManager> findById(Integer mgrId);
}
