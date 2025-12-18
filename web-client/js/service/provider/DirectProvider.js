/**
 * This class executes a query to the backend to solve the direct geodetic task.
 */
export class DirectProvider {

    async getDirectResponse(directRequest) {

try {
    const response = await fetch('http://192.168.0.12:8181/direct', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(directRequest)
    });

    if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

    return await response.json();
} catch(error) {
    alert(`Ошибка отправки данных: ${error.message}`);
    // throw error;
}



    }

}