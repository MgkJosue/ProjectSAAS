import Swal from "sweetalert2";
import http from "../http-common";
import ITrainRoutineData from "../models/TrainRoutine";

const create = async (data: ITrainRoutineData) => {    
  try {
    const response = await http.post<ITrainRoutineData>("/trainRoutines", data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La rutina de entrenamiento ha sido creada correctamente',
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
    return http.get<ITrainRoutineData>(`/trainRoutines/${id}`);
};

const update = async (data: ITrainRoutineData) => {
  try {    
    const response = await http.put<ITrainRoutineData>(`/trainRoutines/${data.id}`, data);
    if(response.status === 200){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La rutina de entrenamiento ha sido actualizada',
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
      const response = await  http.delete<string>(`/trainRoutines/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'La rutina de entrenamiento ha sido eliminada',
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
  const urlRequest : string = "/trainRoutines/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<ITrainRoutineData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/trainRoutines/count");
  return response.data;
};

const TrainRoutineService = {
  create,
  retrieve,
  update,
  remove,
  list,
  count
};
export default TrainRoutineService;

