package saas.personaltrain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saas.personaltrain.models.TrainRoutine;


@Repository
public interface TrainRoutineRepository extends JpaRepository<TrainRoutine,Long>{
    
}
