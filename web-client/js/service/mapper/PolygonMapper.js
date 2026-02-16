
/**
 * This class provides methods for converting 
 * models and dto objects for PolygonService
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class PolygonMapper {

    /**
     * Converts an instance of the PolygonRepositoryinto 
     * an array of strings in the 'pol' format
     * @param {PolygonRepository} polygonRepository 
     * @returns {string[]}
     */
    polygonRepositoryToArray(polygonRepository) {
        const linesArray = new Array();
        let line = '';

        for (let i = 0; i < polygonRepository.size(); i++) {
            line = '';
            line += `${polygonRepository.getStationName(i)} `;
            line += `${polygonRepository.getHorAngle(i)} `;
            line += `${polygonRepository.getHorDistance(i)} `;
            line += `${polygonRepository.getElevation(i)} `;
            if (polygonRepository.getStatus(i)) {
                line += `${polygonRepository.getStationX(i)} `;
                line += `${polygonRepository.getStationY(i)} `;
                line += `${polygonRepository.getStationZ(i)}`;
            } else {
                line += `Not `;
                line += `Not `;
                line += `Not`;

            }
            
            linesArray.push(line);
        }

        return linesArray;
    }

    /**
     * converts an array of strings in the 'pol' format to 
     * an instance of the 'PolygonRepository' object
     * @param {string[]} linesArray 
     * @param {PolygonRepository} polygonRepository 
     */
    arrayToPolygonRepository(linesArray, polygonRepository) {
        for (let line of linesArray) {
            let itemsArray = line.trim().split(/\s+/);
            if (itemsArray.length == 7) {
                polygonRepository.addNewStation();
                polygonRepository.saveStationName(-1, itemsArray.at(0));
                polygonRepository.saveHorAngle(-1, itemsArray.at(1));
                polygonRepository.saveHorDistance(-1, itemsArray.at(2));
                polygonRepository.saveElevation(-1, itemsArray.at(3));
                polygonRepository.saveStationX(-1, itemsArray.at(4));
                polygonRepository.saveStationY(-1, itemsArray.at(5));
                polygonRepository.saveStationZ(-1, itemsArray.at(6));
                if ((itemsArray.at(4) == "Not") || (itemsArray.at(5) == "Not") || (itemsArray.at(6) == "Not")) {
                    polygonRepository.saveStatus(-1, false);
                } else {
                    polygonRepository.saveStatus(-1, true);
                }
            }
        }

        return polygonRepository;
    }

    /**
     * Parses "polygonResponse" and 
     * fills reports after mathematic processing polygon
     * @param {string[]} polygonResponse 
     * @param {Residuals} residuals 
     * @param {string[]} reportCatalog 
     * @param {string[]} reportPlan 
     * @param {string[]} reportElevation 
     */
    polygonResponseToReports(
        polygonResponse, 
        residuals, 
        reportCatalog, 
        reportPlan, 
        reportElevation
    ) {
        const arrayResiduals = [];
        let target = "";

        for (let line of polygonResponse) {

            if (line == "#residuals") {
                target = "residuals";
                continue;
            }

            if (line == "#report-catalog") {
                target = "report-catalog";
                continue;
            }

            if (line == "#report-plan") {
                target = "report-plan";
                continue;
            }

            if (line == "#report-elevation") {
                target = "report-elevation";
                continue;
            }

            switch (target) {

                case "residuals":
                    arrayResiduals.push(line);
                    break;

                case "report-catalog":
                    reportCatalog.push(line);
                    break;

                case "report-plan":
                    reportPlan.push(line);
                    break;

                case "report-elevation":
                    reportElevation.push(line);
                    break;
            }


        }

        if (arrayResiduals.length === 7) {
            for (let line of arrayResiduals) {
                let residual = line.split("=");
                if (residual.length === 2) {
                    switch (residual.at(0)) {

                        case "elevation":
                            residuals.elevation = residual.at(1);
                            break;

                        case "angle":
                            residuals.angle = residual.at(1);
                            break;

                        case "x":
                            residuals.x = residual.at(1);
                            break;

                        case "y":
                            residuals.y = residual.at(1);
                            break;

                        case "absolute":
                            residuals.ablolute = residual.at(1);
                            break;

                        case "relative":
                            residuals.relative = residual.at(1);
                            break;

                        case "perimeter":
                            residuals.perimeter = residual.at(1);
                            break;
                        
                    }

                }

            }

        }

    }

}