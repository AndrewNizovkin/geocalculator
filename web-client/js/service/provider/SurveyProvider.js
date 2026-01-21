/**
 * This class provides methods 
 * for getting data from external sources.
 */
export class SurveyProvider {

    /**
     * Reads the lines of a text file into
     *  an array of strings
     * @param {File} pathToFile 
     * @returns {string[]}
     */
    async getStringArrayFromDevice(fileTah) {
        let reader = new FileReader();

        try {
            reader.readAsText(fileTah);
            reader.onload =  async function (event) {
                let linesArray = await event.target.result.split('\n');
                alert(linesArray.lenth)
                return linesArray;
                
            }

        } catch {
            alert('Ошибка чтения файла');

        }

    }

    /**
     * 
     * @param {string} pathToFile 
     * @param {string[]} stringArray 
     */
    saveToDevice(pathToFile, stringArray) {

    }

}