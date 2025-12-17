/**
 * This class executes a query to the backend to solve the inverse geodetic task.
 */
export class InverseProvider {

   getInverseResponse(inverseRequest) {

        alert(JSON.stringify(inverseRequest));

    let jsonResponse = {
    "baseX": 100000,
    "baseY": 500000,
    "baseZ": 100000,
    "targetX": 200000,
    "targetY": 200000,
    "targetZ": 200000,
    "direction": 1038366,
    "horDistance": 316228,
    "inclinedDistance": 331662,
    "tiltAngle": 63174,
    "elevation": 100000
};

try {

    fetch('http://192.168.0.12:8181/inverse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inverseRequest)
    })
    .catch(error => alert(error.message))
    
} catch(error) {
    alert('Ошибка: ', error.message);
}

        // let response = await fetch('http://192.168.0.12:8181/inverse', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json;charset=utf-8',
        //         'Access-Control-Allow-Origin': 'http://192.168.0.12:8181/inverse'
        //     },
        //     body: JSON.stringify(jsonRequest)
        // });

        // if(!response.ok) {
        //     alert(response.status);
        // }

        // alert(response.status);

        // let reault = await response.json();
        
        // alert(reault);





        return jsonResponse;


        // fetch('localhost:8181/inverse', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json;charset=utf-8'
        //     },
        //     body: jsonRequest
        // });
        
    }
}