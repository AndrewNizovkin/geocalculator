import { SurveyRepository } from "../../repository/SurveyRepository.js";

/**
 * This class provides methods for converting models and dto objects.
 */
export class SurveyMapper {

    /**
     * converts an array of strings in the 'tah' format to 
     * an instance of the 'SurveyRepository' object
     * @param {string[]} arraySurvey array of strings in the 'tah' format
     * @returns {SurveyRepository}
     */ 
    arrayToSurveyRepository(arraySurvey) {
        
    }

    /**
     * converts an instance of the 'SurveyRepository' object into 
     * an array of strings in the 'tah' format
     * @param {SurveyRepository} surveyRepository 
     * @returns {string[]}
     */
    surveyRepositoryToArray(surveyRepository) {
        let arrayTah = [];

        return arrayTah;
    }

}