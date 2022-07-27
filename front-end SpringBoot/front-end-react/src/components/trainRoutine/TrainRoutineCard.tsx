import React, { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link } from 'react-router-dom';
import { useParams } from "react-router-dom";
import ITrainRoutineModel from "../../models/TrainRoutine";
import TrainRoutineService from "../../services/TrainRoutineServices";

export const TrainRoutineCard = () => {
  const { id }= useParams();

  const [trainRoutine, setTrainRoutine] = useState<ITrainRoutineModel>();

  useEffect(() => {
    if (id)
      getTrainRoutine(id);
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

    return (
      <div>
      { 
        trainRoutine ? (
          <div>          
          <h2>{trainRoutine.nameRoutine}</h2>
          <p>{trainRoutine.description}</p>
          <ul>
            <li> <strong>Tiempo de inicio de la rutina de entrenamiento :</strong>  {trainRoutine.timeBegin} minutos</li>
            <li> <strong>Tiempo de final de la rutina de entrenamiento :</strong>  {trainRoutine.timeEnd} minutos</li>
            <li>Número de ejercicios: {trainRoutine.numberOfExercises}</li>        
          </ul>
          <br />
              <div className="btn-group" role="group">                
                <Link to={"/trainRoutines"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" className="btn btn-danger">
                  <FaTrash />Eliminar
                </button>
              </div>
          </div>

        ) : 
        ( 
          <h1>No hay una rutina de entrenamiento activa</h1>
        )
      }
      </div>
    );
}

