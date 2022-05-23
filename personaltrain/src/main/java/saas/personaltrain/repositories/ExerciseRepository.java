package saas.personaltrain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saas.personaltrain.models.Exercise;


@Repository
public interface ExerciseRepository extends JpaRepository <Exercise,Long> {
    
}
