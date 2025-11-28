import { UnitsConverter } from "./UnitsConverter";

describe('UnitConverter', function() {

    const unitConverter = new UnitsConverter();

    it('UnitConverter', function() {
        // const unitConverter = new UnitsConverter();
        assert.strictEqual(unitConverter.meterToMillimeter("100.100"), 10000);
        // assert.strictEqual(5,5);
    });


});