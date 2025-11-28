import {InverseService} from './service/InverseService.js';
import { UnitsConverter } from './service/mapper/UnitsConverter.js';
// import (UnitsConverter) from ''

alert('Превет, создаю inverseService');

// let inverseService = new InverseService();

// alert(`baseX: ${inverseService.getBaseX()} \nbaseY: ${inverseService.getBaseY()}`);

// inverseService.setBaseX(2000);
// inverseService.setBaseY(3000);

// alert(`baseX: ${inverseService.getBaseX()} \nbaseY: ${inverseService.getBaseY()}`);

let unitsConverter = new UnitsConverter();

alert(`1000.999м. = ${unitsConverter.meterToMillimeter('1000.999')}`);