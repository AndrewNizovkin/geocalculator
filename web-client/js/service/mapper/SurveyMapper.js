/**
 * This class provides methods for converting models and dto objects.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class SurveyMapper {

    /**
     * converts an array of strings in the 'tah' format to 
     * an instance of the 'SurveyRepository' object
     * @param {string[]} object array of strings in the 'tah' format
     * @returns {SurveyRepository}
     */ 
    arrayToSurveyRepository(object, surveyRepository) {
        // let surveyRepository = new SurveyRepository();

        let linesArray = [];
        object.forEach(element => {
            linesArray.push(element);
        });

        if(linesArray.length > 0) {
            let line = linesArray.shift();
            let itemsArray = [];
            let currentSurveyStation = 0;
            while(line.trim() != "//" && linesArray.length > 1) {
                itemsArray = line.trim().split(/\s+/);
                if(itemsArray.length == 8) {
                    surveyRepository.addNewStation();

                    surveyRepository.saveStationName(-1, itemsArray[0]);
                    surveyRepository.saveStationX(-1, itemsArray[1]);
                    surveyRepository.saveStationY(-1, itemsArray[2]);
                    surveyRepository.saveStationZ(-1, itemsArray[3]);
                    surveyRepository.saveStationHeight(-1, itemsArray[4]);
                    surveyRepository.saveOrName(-1, itemsArray[5]);
                    surveyRepository.saveOrX(-1, itemsArray[6]);
                    surveyRepository.saveOrY(-1, itemsArray[7]);
                }
                
                line = linesArray.shift();
            }

            line = linesArray.shift();
            while(linesArray.length > 0) {
                while(line.trim() == "//") {
                    line = linesArray.shift();
                }

                itemsArray = line.trim().split(/\s+/);
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
        let line = '';

        for (let i = 0; i < surveyRepository.size(); i++) {
            line = '';
            line += `${surveyRepository.getStationName(i)} `;
            line += `${surveyRepository.getStationX(i)} `;
            line += `${surveyRepository.getStationY(i)} `;
            line += `${surveyRepository.getStationZ(i)} `;
            line += `${surveyRepository.getStationHeight(i)} `;
            line += `${surveyRepository.getOrName(i)} `;
            line += `${surveyRepository.getOrX(i)} `;
            line += `${surveyRepository.getOrY(i)}`;
            arrayTah.push(line);
        }

        arrayTah.push("//");

        for (let i = 0; i < surveyRepository.size(); i++) {
            for (let j = 0; j < surveyRepository.measurementSize(i); j++) {
                line = '';
                line += `${surveyRepository.getTargetName(i, j)} `;
                line += `${surveyRepository.getTargetDistance(i, j)} `;
                line += `${surveyRepository.getTargetDirection(i, j)} `;
                line += `${surveyRepository.getTargetTiltAngle(i, j)} `;
                line += `${surveyRepository.getTargetHeight(i, j)} `;
                line += `${i}`;
                arrayTah.push(line);
            }
            arrayTah.push("//");
        }

        return arrayTah;
    }

    /**
     * Converts an instance of the 'SurveyRepository' object into 
     * surveyRequest for send to backend server
     * @param {SurveyRepository} surveyRepository 
     * @returns {string[]}
     */
    surveyRepositoryToSurveyRequest(surveyRepository) {
        let surveyRequest = [];
        let line = '';

        for (let i = 0; i < surveyRepository.size(); i++) {
            line = '';
            line += `${surveyRepository.getStationName(i)} `;
            line += `${surveyRepository.getStationX(i)} `;
            line += `${surveyRepository.getStationY(i)} `;
            line += `${surveyRepository.getStationZ(i)} `;
            line += `${surveyRepository.getStationHeight(i)} `;
            line += `${surveyRepository.getOrDirection(i)} `;
            line += `${surveyRepository.getOrName(i)} `;
            line += `${surveyRepository.getOrX(i)} `;
            line += `${surveyRepository.getOrY(i)}`;
            surveyRequest.push(line);
        }

        surveyRequest.push("//");

        for (let i = 0; i < surveyRepository.size(); i++) {
            for (let j = 0; j < surveyRepository.measurementSize(i); j++) {
                line = '';
                line += `${surveyRepository.getTargetName(i, j)} `;
                line += `${surveyRepository.getTargetDistance(i, j)} `;
                line += `${surveyRepository.getTargetDirection(i, j)} `;
                line += `${surveyRepository.getTargetTiltAngle(i, j)} `;
                line += `${surveyRepository.getTargetHeight(i, j)} `;
                line += `${i}`;
                surveyRequest.push(line);
            }
            surveyRequest.push("//");
        }

        return surveyRequest;
    }

    /**
     * Parses "suveyResponse" and fills 
     * reports after mathematic processing survey data
     * @param {string[]} suveyResponse 
     * @param {string[]} reportSurveyProcessing 
     * @param {string[]} reportSurveyCatalog 
     */
    surveyResponseToReports(
        suveyResponse,
        reportSurveyProcessing,
        reportSurveyCatalog
    ) {
        let target = true;

        for (let line of suveyResponse) {
            if (line === "//") continue;
            if (line === "#survey-report") {
                target = true;
                continue;
            }
            if (line === "#processing-report") {
                target = false;
                continue;
            }

            if (target) {
                reportSurveyCatalog.push(line);
            } else {
                reportSurveyProcessing.push(line);
            }
        }

    }

}