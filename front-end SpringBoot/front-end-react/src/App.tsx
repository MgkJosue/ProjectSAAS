import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Home } from "./components/Home";
import { TrainRoutineList } from "./components/trainRoutine/TrainRoutineList";
import { TrainRoutineForm } from "./components/trainRoutine/TrainRoutineForm";
import { TrainRoutineCard } from "./components/trainRoutine/TrainRoutineCard";
import React from "react";
import { ExerciseList } from "./components/Exercise/ExerciseList";
import { ExerciseForm } from "./components/Exercise/ExerciseForm";
import { ExerciseCard } from "./components/Exercise/ExerciseCard";

const title = "Online Test";
const description = "Aplicación web para la automatización de cuestionarios en línea";

const App: React.FC = () => {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">        
        <Link to={"/"}  className="navbar-brand">
          Train 
        </Link>
        <div className="navbar-nav mr-auto">       
        </div>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/trainRoutines"} className="nav-link">
              Rutinas de entrenamiento
            </Link>
          </li>      
          <li className="nav-item">
            <Link to={"/exercises"} className="nav-link">
              Ejercicios
            </Link>
          </li>      
        </div>
      </nav>
      <div className="container mt-3">
        <Routes>  
          <Route path="/" element={<Home title={title} description={description} />} />          
          <Route path="/trainRoutines" element={<TrainRoutineList />} />          
          <Route path="/trainRoutines/create" element={<TrainRoutineForm />} />    
          <Route path="/trainRoutines/retrieve/:id" element={<TrainRoutineCard/>} />      
          <Route path="/trainRoutines/update/:id" element={<TrainRoutineForm />} />    

          <Route path="/" element={<Home title={title} description={description} />} />          
          <Route path="/exercises" element={<ExerciseList />} />          
          <Route path="/exercises/create" element={<ExerciseForm />} />    
          <Route path="/exercises/retrieve/:id" element={<ExerciseCard/>} />      
          <Route path="/exercises/update/:id" element={<ExerciseForm />} />   

        </Routes>
      </div>

    </div>
  );
}
export default App;
