import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IExerciseModel from '../../models/Exercise';
import ExerciseService from '../../services/ExerciseServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const ExerciseList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [exercises, setExercises] = useState<Array<IExerciseModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    const [loading, setLoading] = useState(false);

    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listExercises(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listExercises(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listExercises = (page: number, size: number) => {
       ExerciseService.list(page, size)
         .then((response: any) => {
           setExercises(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      ExerciseService.count().then((response: any) =>{
        let itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeExercise = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar el ejercicio?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {    
            setLoading(true)

            if (result.isConfirmed) {
                ExerciseService.remove(id)
                .then((response: any) => {
                  listExercises(0,itemsPerPage);
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
            <h1>Hay {itemsCount} ejercicio(s)</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre del ejercicio</th>
                            <th>Descripción</th>
                            <th>
                              <Link to={"/exercises/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {exercises && exercises.map((Exercise, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                
                                <td>{Exercise.name}</td>                                
                                <td>{Exercise.muscularGroup}</td>                                
                                <td>{Exercise.description}</td>
                                <td>
                        
                                <div className="btn-group" role="group">
                                <Link to={"/exercises/retrieve/" + Exercise.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/exercises/update/" + Exercise.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeExercise(Exercise.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination "
                  breakLabel="..."
                  nextLabel="siguiente >"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousLabel="< anterior" />

            </div>            
        </div>
     );
}


