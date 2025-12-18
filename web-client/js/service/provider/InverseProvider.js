/**
 * This class executes a query to the backend to solve the inverse geodetic task.
 */
export class InverseProvider {

   async getInverseResponse(inverseRequest) {

try {
    const response = await fetch('http://192.168.0.12:8181/inverse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inverseRequest)
    });

    if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

    return await response.json();
} catch(error) {
    alert(`Ошибка отправки данных: ${error.message}`);
    // throw error;
}
       
    }
}