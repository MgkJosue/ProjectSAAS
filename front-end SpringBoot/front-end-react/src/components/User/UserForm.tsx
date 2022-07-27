import React, { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IExerciseModel from "../../models/Exercise";
import ExerciseService from "../../services/ExerciseServices";

export const ExerciseForm = () => {
  
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialExerciseModel : IExerciseModel = {
        id: null,
        name : "",
        muscularGroup:"",
        description :"" 
    };

    //Hooks para gestionar el modelo
    const [exercise, setExercise] = useState<IExerciseModel>(initialExerciseModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setExercise({ ...exercise, [name]: value });
    };

    const saveExercise = () => {        
      if(exercise.id !== null)
      {
        ExerciseService.update(exercise)
        .then((response: any) => {
          navigate("/exercises");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
        ExerciseService.create(exercise)
          .then((response: any) => {    
            navigate("/exercises");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

    useEffect(() => {
      if(id){
        getExercise(id);
        console.log(id);
      }
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

    return ( //JSX
      <div className="submit-form">       
          <div>
            { exercise.id !== null ? (<h1>Ejercicio actualizado en {exercise.name}</h1>) : (<h1>Registro de nuevo ejercicio</h1>) }            
            <div className="form-group">
            <label htmlFor="name">Nombre del ejercicio</label>
            <input            
              type="string"
              className="form-control"
              placeholder="Ingrese el nombre del ejercicio"
              id="name"
              required
              value={exercise.name}
              onChange={handleInputChange}
              name="name"
            />

            <label htmlFor="muscularGroup">Grupo Muscular</label>
            <input            
              type="string"
              className="form-control"
              placeholder="Ingrese el nombre del grupo muscular"
              id="muscularGroup"
              required
              value={exercise.muscularGroup}
              onChange={handleInputChange}
              name="muscularGroup"
            />

            <label htmlFor="description">Descripción</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Ingrese la descripción del ejercicio"
              id="description"
              required
              onChange={handleInputChange}
              value={exercise.description}
              name="description"
            />
            <br />
              <div className="btn-group" role="group">                
                <Link to={"/exercises"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" onClick={saveExercise} className="btn btn-success">
                  <FaSave />Guardar
                </button>
              </div>
            </div>
          </div>        
      </div>        
    );

}


