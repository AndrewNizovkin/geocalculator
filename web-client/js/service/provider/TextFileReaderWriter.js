/**
 * This class provides methods for working 
 * with the device's file system.
 */
export class TextFileReaderWriter {

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

    /**
     * Writes the contents of an array of strings to a file
     * @param {File} pathToFile 
     * @param {string[]} linesArray 
     */
    static async writeToTextFile(fileOrHandle, linesArray) {

        if (!(fileOrHandle instanceof File)) {
            throw new TypeError('Первый аргумент должен быть объектом File или FileSystemFileHandle.');
        }


        const writableStream = await fileOrHandle.createWritable();

        for (const line of linesArray) {
            await writableStream.write(line + "\n");
        }

        await writableStream.close();
    }        



}