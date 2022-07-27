import React, { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import ITrainRoutineModel from "../../models/TrainRoutine";
import TrainRoutineService from "../../services/TrainRoutineServices";

export const TrainRoutineForm = () => {
  
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialTrainRoutineModel : ITrainRoutineModel = {
        id: null,
        nameRoutine : "",
        timeBegin : 1,
        timeEnd : 180,
        numberOfExercises: 1,
        description :"" 
    };

    //Hooks para gestionar el modelo
    const [trainRoutine, setTrainRoutine] = useState<ITrainRoutineModel>(initialTrainRoutineModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setTrainRoutine({ ...trainRoutine, [name]: value });
    };

    const saveTrainRoutine = () => {        
      if(trainRoutine.id !== null)
      {
        TrainRoutineService.update(trainRoutine)
        .then((response: any) => {
          navigate("/trainRoutines");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
        TrainRoutineService.create(trainRoutine)
          .then((response: any) => {    
            navigate("/trainRoutines");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

    useEffect(() => {
      if(id){
        getTrainRoutine(id);
        console.log(id);
      }
    }, [id]);

    const getTrainRoutine = (id: any) => {
      TrainRoutineService.retrieve(id)
        .then((response: any) => {
          setTrainRoutine(response.data); //Víncula el resultado del servicio con la función del Hook useState
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
   };
//que es esto?
    return ( //JSX
      <div className="submit-form">       
          <div>
            { trainRoutine.id !== null ? (<h1>Rutina de entrenamiento actualizada en {trainRoutine.nameRoutine}</h1>) : (<h1>Registro de nueva rutina de entrenamiento</h1>) }            
            <div className="form-group">
            <label htmlFor="nameRoutine">Nombre Rutina de Entrenamiento</label>
            <input
              type="text"
              placeholder="Ingrese el nombre de la rutina de entrenamiento"
              className="form-control"
              id="nameRoutine"
              required
              value={trainRoutine.nameRoutine}
              onChange={handleInputChange}
              name="nameRoutine"
            />

            <label htmlFor="timeBegin">Tiempo de inicio(Minutos)</label>
            <input            
              type="number"
              className="form-control"
              id="timeBegind"
              max="15"
              min="1"
              required
              value={trainRoutine.timeBegin}
              onChange={handleInputChange}
              name="timeBegin"
            />

  

            <label htmlFor="timeEnd">Tiempo final (Minutos)</label>
            <input            
              type="number"
              className="form-control"
              id="timeEnd"
              max="180"
              min="20"
              required
              value={trainRoutine.timeEnd}
              onChange={handleInputChange}
              name="timeEnd"
            />

            <label htmlFor="numberOfExercises">Número de ejercicios</label>
            <input            
              type="number"
              className="form-control"
              id="numberOfExercises"              
              min="1"
              required
              value={trainRoutine.numberOfExercises!}
              onChange={handleInputChange}
              name="numberOfQuestions"
            />

            <label htmlFor="description">Descripción</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Ingrese la descripción de la rutina de entrenamiento"
              id="description"
              required
              onChange={handleInputChange}
              value={trainRoutine.description}
              name="description"
            />
            <br />
              <div className="btn-group" role="group">                
                <Link to={"/trainRoutines"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" onClick={saveTrainRoutine} className="btn btn-success">
                  <FaSave />Guardar
                </button>
              </div>
            </div>
          </div>        
      </div>        
    );

}

