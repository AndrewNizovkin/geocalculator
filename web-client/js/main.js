import {InverseService} from './service/inverseService.js';

alert('Превет, создаю inverseService');

let inverseService = new InverseService();

alert(`baseX: ${inverseService.getBaseX()} \nbaseY: ${inverseService.getBaseY()}`);
// alert(`baseY: ${baseX}`);

inverseService.setBaseX(2000);
inverseService.setBaseY(3000);

alert(`baseX: ${inverseService.getBaseX()} \nbaseY: ${inverseService.getBaseY()}`);
