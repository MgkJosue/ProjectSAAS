package saas.personaltrain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saas.personaltrain.models.Learning;


@Repository
public interface LearningRepository extends JpaRepository <Learning,Long>{
    
}
