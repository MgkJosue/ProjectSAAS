import Swal from "sweetalert2";
import http from "../http-common";
import IExerciseData from "../models/Exercise";

const create = async (data: IExerciseData) => {    
  try {
    const response = await http.post<IExerciseData>("/exercises", data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'El ejercicio ha sido creado correctamente',
        confirmButtonText: 'Aceptar'    
      });
    }
    console.log(response);
  } catch (err) {
    console.log(err);
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
};

const retrieve = async (id: number) => {
    return http.get<IExerciseData>(`/exercises/${id}`);
};

const update = async (data: IExerciseData) => {
  try {    
    const response = await http.put<IExerciseData>(`/exercises/${data.id}`, data);
    if(response.status === 200){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'El ejercicio ha sido actualizado',
        confirmButtonText: 'Aceptar'    
      });
    }

  } catch (error) {
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
    console.error;
  }
    
};

const remove = async (id: number) => {
    try {
      const response = await  http.delete<string>(`/exercises/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'El ejercicio ha sido eliminado',
          confirmButtonText: 'Aceptar'    
        });
      }
    } catch (error) {
      Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
    }

};

const list = (page: number, size: number, sort? : String) => {
  const urlRequest : string = "/exercises/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<IExerciseData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/exercises/count");
  return response.data;
};

const ExerciseService = {
  create,
  retrieve,
  update,
  remove,
  list,
  count
};
export default ExerciseService;


