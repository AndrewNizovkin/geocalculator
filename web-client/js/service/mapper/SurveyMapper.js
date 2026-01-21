import { SurveyRepository } from "../../repository/SurveyRepository.js";

/**
 * This class provides methods for converting models and dto objects.
 */
export class SurveyMapper {

    /**
     * converts an array of strings in the 'tah' format to 
     * an instance of the 'SurveyRepository' object
     * @param {string[]} linesArray array of strings in the 'tah' format
     * @returns {SurveyRepository}
     */ 
    arrayToSurveyRepository(linesArray) {
        
        for(let i = 0; i < linesArray.length; i++) {
            console.log(linesArray[i]);
        }

        let surveyRepository = new SurveyRepository();
        alert('Тип репозитория' + typeof(surveyRepository));

        if(linesArray.length > 0) {
            let line = linesArray.shift();
            let itemsArray = [];
            let currentSurveyStation = 0;
            while(line != "//" && linesArray > 1) {
                itemsArray = line.split(" ");
                if(itemsArray.length == 8) {
                    surveyRepository.addNewStation();

                    surveyRepository.saveStationName(itemsArray[0]);
                    surveyRepository.saveStationX(itemsArray[1]);
                    surveyRepository.saveStationY(itemsArray[2]);
                    surveyRepository.saveStationZ(itemsArray[3]);
                    surveyRepository.saveStationHeight(itemsArray[4]);
                    surveyRepository.saveOrName(itemsArray[5]);
                    surveyRepository.saveOrX(itemsArray[6]);
                    surveyRepository.saveOrY(itemsArray[7]);
                }
                
                line = linesArray.shift();
            }

            line = linesArray.shift();
            while(linesArray.length > 0) {
                while(line == "//") {
                    line = linesArray.shift();
                }

                itemsArray = line.split(" ");
                if(itemsArray.length == 6) {
                    currentSurveyStation = +itemsArray[5];
                    surveyRepository.addNewMeasurement(currentSurveyStation);
                    surveyRepository.saveTargetName(currentSurveyStation, -1, itemsArray[0]);
                    surveyRepository.saveTargetDistance(currentSurveyStation, -1, itemsArray[1]);
                    surveyRepository.saveTargetDirection(currentSurveyStation, -1, itemsArray[2]);
                    surveyRepository.saveTargetTiltAngle(currentSurveyStation, -1, itemsArray[3]);
                    surveyRepository.saveTargetHeight(currentSurveyStation, -1, itemsArray[4]);
                }
                
                line = linesArray.shift();
            }
        }
        
        return surveyRepository;
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