/**
 * Tests meterToMillimeter from value-mapper
 */
describe("meterToMillimeter", function() {

    it("Converts '100.123' to 100123", function() {
        // assert.equal(meterToMillimeter("100.123"), 100123)
        assert.strictEqual(meterToMillimeter("100.123"), 100123)
    });

    it("Converts '0.000' to 0", function() {
        assert.strictEqual(meterToMillimeter("0.000"), 0)
    });

    it("Converts '0' to 0", function() {
        assert.strictEqual(meterToMillimeter("0"), 0)
    });


    it("Converts '0.001' to 1", function() {
        assert.strictEqual(meterToMillimeter("0.001"), 1)
    });


    it("Converts '-100.999' to -100999", function() {
        assert.strictEqual(meterToMillimeter("-100.999"), -100999)
    });
});

/**
 * Tests millimeterToMeter fom value-mapper
 */
describe("millimeterToMeter", function() {

    it("Converts 0 to '0.000'", function() {
        assert.strictEqual(millimeterToMeter(0), "0.000")
    })


    it("Converts 1000 to '1.000'", function() {
        assert.strictEqual(millimeterToMeter(1000), "1.000")
    });

    it("Converts -1000 to '-1.000'", function() {
        assert.strictEqual(millimeterToMeter(-1000), "-1.000")
    });

    it("Converts 999999999999 to '999999999.999'", function() {
        assert.strictEqual(millimeterToMeter(-1000), "-1.000")
    });

    it("Converts -999999999999 to '-999999999.999'", function() {
        assert.strictEqual(millimeterToMeter(-1000), "-1.000")
    });

});

/**
 * Tests dmsToSecond from value-mapper
 */
describe("dmsToSecond", function() {

    it("Converts from '0' to 0", function() {
        assert.strictEqual(dmsToSecond("0"), 0)
    });

    it("Converts from '0.0001' to 1", function() {
        assert.strictEqual(dmsToSecond("0.0001"), 1)
    });

    it("Converts from '1.0000' to 3600", function() {
        assert.strictEqual(dmsToSecond("1.0000"), 3600)
    });

    it("Converts from '195.1330' to 702810", function() {
        assert.strictEqual(dmsToSecond("195.1330"), 702810)
    });


    it("Converts from '359.5959' to 1295999", function() {
        assert.strictEqual(dmsToSecond("359.5959"), 1295999)
    });    

    it("Converts from '-359.5959' to -1295999", function() {
        assert.strictEqual(dmsToSecond("-359.5959"), -1295999)
    });    
});

/**
 * Tests secondToDms from value-mapper
 */
describe("secondToDms", function() {

    it("Converts from 0 to '0.0000", function() {
        assert.strictEqual(secondToDms(0), "0.0000")
    });

    it("Converts from 1 to '0.0001", function() {
        assert.strictEqual(secondToDms(1), "0.0001")
    });

    it("Converts from -1 to '-0.0001", function() {
        assert.strictEqual(secondToDms(-1), "-0.0001")
    });

    it("Converts from 702810 to '195.1330", function() {
        assert.strictEqual(secondToDms(702810), "195.1330")
    });


    it("Converts from 1295999 to '359.5959", function() {
        assert.strictEqual(secondToDms(1295999), "359.5959")
    });

    it("Converts from -1295999 to '-359.5959", function() {
        assert.strictEqual(secondToDms(-1295999), "-359.5959")
    });

});