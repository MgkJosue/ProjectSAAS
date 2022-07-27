import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import ITrainRoutineModel from '../../models/TrainRoutine';
import TrainRoutineService from '../../services/TrainRoutineServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const TrainRoutineList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [trainRoutines, setTrainRoutines] = useState<Array<ITrainRoutineModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    const [loading, setLoading] = useState(false);

    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listTrainRoutines(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listTrainRoutines(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listTrainRoutines = (page: number, size: number) => {
       TrainRoutineService.list(page, size)
         .then((response: any) => {
           setTrainRoutines(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      TrainRoutineService.count().then((response: any) =>{
        let itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeTrainRoutine = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar la rutina de entrenamiento?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {    
            setLoading(true)

            if (result.isConfirmed) {
                TrainRoutineService.remove(id)
                .then((response: any) => {
                  listTrainRoutines(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });
          
        setLoading(false)
     };
   
    return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} rutina(s) de entrenamiento</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre de la rutina de entrenamiento</th>
                            <th>Tiempo de inicio</th>
                            <th>Tiempo de final</th>
                            <th># Ejercicios</th>
                            <th>Descripción</th>
                            <th>
                              <Link to={"/trainRoutines/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {trainRoutines && trainRoutines.map((TrainRoutine, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                
                                <td>{TrainRoutine.nameRoutine}</td>                                
                                <td>{TrainRoutine.timeBegin} mins</td>
                                <td>{TrainRoutine.timeEnd} mins</td>
                                <td>{TrainRoutine.numberOfExercises}</td>
                                <td>{TrainRoutine.description}</td>
                                <td>
                        
                                <div className="btn-group" role="group">
                                <Link to={"/trainRoutines/retrieve/" + TrainRoutine.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/trainRoutines/update/" + TrainRoutine.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeTrainRoutine(TrainRoutine.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination"
                  breakLabel="..."
                  nextLabel="siguiente >"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousLabel="< anterior"/>

            </div>            
        </div>
     );
}

