/**
 * This class provides methods for working 
 * with the device's file system.
 */
export class TextFileReader {

    /**
     * Reads the lines of a text file into
     *  an array of strings
     * @param {File} pathToFile 
     * @returns {string[]}
     */
    static async readFromTextFile(fileTah) {

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

}