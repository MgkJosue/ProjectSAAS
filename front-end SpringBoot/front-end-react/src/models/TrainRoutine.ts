export default interface ITrainRoutineModel {
    id?: number | null,
    nameRoutine : string,
    timeBegin : number,
    timeEnd : number,
    numberOfExercises?: number |null,
    description : string
}
