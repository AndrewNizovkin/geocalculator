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

        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            
            reader.onload = function() {
                const content = this.result.trim();
                const lines = content.split(/\r\n|\r|\n/);

                resolve(lines);
            };

            reader.onerror = function(event) {
                reject(new Error(`Ошибка при чтении файла: ${event.target.error}`));
            };

            reader.readAsText(fileTah);
        });        

    }

    /**
     * 
     * @param {string} pathToFile 
     * @param {string[]} stringArray 
     */
    saveToDevice(pathToFile, stringArray) {

    }

}