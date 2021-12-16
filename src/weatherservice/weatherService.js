import { getRandomArbitrary } from '../../common/lib/utils.js';

export function GetWeather(call, callback) {

    console.log('Request received: ' + JSON.stringify(call));

    const regEx = /^[aeiouAEIOU]/;

    setTimeout(function () {
        const response = {
            city: call.request.city,
            weather: regEx.test(call.request.city) ? "rainy" : "sunny"
        };

        callback(null, response);
    }, getRandomArbitrary(1, 3) * 1000);
}