import React, { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link } from 'react-router-dom';
import { useParams } from "react-router-dom";
import IExerciseModel from "../../models/Exercise";
import ExerciseService from "../../services/ExerciseServices";

export const ExerciseCard = () => {
  const { id }= useParams();

  const [exercise, setExercise] = useState<IExerciseModel>();

  useEffect(() => {
    if (id)
      getExercise(id);
  }, [id]);

  const getExercise = (id: any) => {
    ExerciseService.retrieve(id)
      .then((response: any) => {
        setExercise(response.data); //Víncula el resultado del servicio con la función del Hook useState
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });
 };

    return (
      <div>
      { 
        exercise ? (
          <div>          
          <h2>{exercise.name}</h2>
          <p>{exercise.description}</p>
          <ul>
            <li> <strong>Grupo muscular a entrenar: </strong>  {exercise.muscularGroup}</li>
          </ul>
          <br />
              <div className="btn-group" role="group">                
                <Link to={"/exercises"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" className="btn btn-danger">
                  <FaTrash />Eliminar
                </button>
              </div>
          </div>

        ) : 
        ( 
          <h1>No hay un ejercicio activo</h1>
        )
      }
      </div>
    );
}


