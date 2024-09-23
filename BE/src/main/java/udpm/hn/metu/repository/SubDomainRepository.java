package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.SubDomain;

@Repository
public interface SubDomainRepository extends JpaRepository<SubDomain, String> {
}
