// import sdfs from '../../js/service/mapper/UnitsConverter.js'

/**
 * Tests meterToMillimeter from UnitsConverter
 */
describe("meterToMillimeter", function() {

 
    it("Converts '100.123' to 100123", function() {
        const rezult = meterToMillimeter('100.123');
        assert.strictEqual(rezult, 100123);
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
        assert.strictEqual(secondToDms(0), "0.0000");
    });

    it("Converts from 1 to '0.0001", function() {
        assert.strictEqual(secondToDms(1), "0.0001");
    });

    it("Converts from -1 to '-0.0001", function() {
        assert.strictEqual(secondToDms(-1), "-0.0001");
    });

    it("Converts from 702810 to '195.1330", function() {
        assert.strictEqual(secondToDms(702810), "195.1330");
    });


    it("Converts from 1295999 to '359.5959", function() {
        assert.strictEqual(secondToDms(1295999), "359.5959");
    });

    it("Converts from -1295999 to '-359.5959", function() {
        assert.strictEqual(secondToDms(-1295999), "-359.5959");
    });

});

/**
 * Tests isValidName
 */
describe("isValidName", function() {

    it("'name' is valid value of name", function() {
        let result = isValidName("name");
        assert.isTrue(result);
    });

    it("'Дружба-Мир' is valid value of name", function() {
        let result = isValidName("Дружба-Мир");
        assert.isTrue(result);
    });

    it("'Маломечетный' is valid value of name", function() {
        let result = isValidName("Маломечетный");
        assert.isTrue(result);
    });

    it("';%?*()_+' is valid value of name", function() {
        let result = isValidName(";%?*()_+");
        assert.isTrue(result);
    });

    it("'0123456789' is valid value of name", function() {
        let result = isValidName("0123456789");
        assert.isTrue(result);
    });

    it("'/name' is not valid value of name", function() {
        let result = isValidName("/name");
        assert.isFalse(result);
    });

    it("''name' is not valid value of name", function() {
        let result = isValidName("'name");
        assert.isFalse(result);
    });

    it("'n ame' is not valid value of name", function() {
        let result = isValidName("n ame");
        assert.isFalse(result);
    });

    it("'#name' is not valid value of name", function() {
        let result = isValidName("#name");
        assert.isFalse(result);
    });

});











/**
 * Tests isValidDigitalNumber
 */
describe("isValidDigitalNumber", function() {

    it("'0' is valid digital number", function() {
        let rezult = isValidDigitalNumber("0");
        assert.isTrue(rezult);
    });

    it("'1' is valid digital number", function() {
        let rezult = isValidDigitalNumber("1");
        assert.isTrue(rezult);
    });

    it("'-1' is valid digital number", function() {
        let rezult = isValidDigitalNumber("-1");
        assert.isTrue(rezult);
    });

    it("'0.00001' is valid digital number", function() {
        let rezult = isValidDigitalNumber("0.00001");
        assert.isTrue(rezult);
    });

    it("'-0.00001' is valid digital number", function() {
        let rezult = isValidDigitalNumber("-0.00001");
        assert.isTrue(rezult);
    });

    it("'12345678912345678.1234' is valid digital number", function() {
        let rezult = isValidDigitalNumber("12345678912345678.1234");
        assert.isTrue(rezult);
    });

    it("'-12345678912345678.1234' is valid digital number", function() {
        let rezult = isValidDigitalNumber("-12345678912345678.1234");
        assert.isTrue(rezult);
    });


    it("'/' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("/");
        assert.isFalse(rezult);
    });
    
    it("'' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("");
        assert.isFalse(rezult);
    });

    it("'asdf' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("asdf");
        assert.isFalse(rezult);
    });

    it("'123 124' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("123 124");
        assert.isFalse(rezult);
    });

    it("'123.124.2354' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("123.124.2354");
        assert.isFalse(rezult);
    });

    it("'--12.34' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("--12.34");
        assert.isFalse(rezult);
    });

    it("'1234.' is not valid digital number", function() {
        let rezult = isValidDigitalNumber("1234.");
        assert.isFalse(rezult);
    });
});

/**
 * Tests isValiisValidPositiveNumber
 */
describe("isValidPositiveNumber", function() {

    it("'0' is valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("0");
        assert.isTrue(rezult);
    });

    it("'1' is valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("1");
        assert.isTrue(rezult);
    });

    it("'0.00001' is valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("0.00001");
        assert.isTrue(rezult);
    });

    it("'12345678912345678.1234' is valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("12345678912345678.1234");
        assert.isTrue(rezult);
    });

    it("'-12345678912345678.1234' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("-12345678912345678.1234");
        assert.isFalse(rezult);
    });

    it("'-0.00001' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("-0.00001");
        assert.isFalse(rezult);
    });

    it("'/' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("/");
        assert.isFalse(rezult);
    });
    
    it("'' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("");
        assert.isFalse(rezult);
    });

    it("'asdf'is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("asdf");
        assert.isFalse(rezult);
    });

    it("'123 124' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("123 124");
        assert.isFalse(rezult);
    });

    it("'123.124.2354' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("123.124.2354");
        assert.isFalse(rezult);
    });

    it("'--12.34' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("--12.34");
        assert.isFalse(rezult);
    });

    it("'1234.' is not valid value of positive digital number", function() {
        let rezult = isValidPositiveNumber("1234.");
        assert.isFalse(rezult);
    });
});


/**
 * Tests isValidHorizontalAngle
 */
describe("isValidHorizontalAngle", function() {

    it("'0.0000' is valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("0.0000");
        assert.isTrue(rezult);
    });

    it("'1.1234' is valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("1.1234");
        assert.isTrue(rezult);
    });

    it("'90.0000' is valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("90.0000");
        assert.isTrue(rezult);
    });

    it("'270.0000' is valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("270.0000");
        assert.isTrue(rezult);
    });

    it("'359.5959' is valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("359.5959");
        assert.isTrue(rezult);
    });

    it("'123.3245' is valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("123.3245");
        assert.isTrue(rezult);
    });

    it("'-123.3245' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("-123.3245");
        assert.isFalse(rezult);
    });

    it("'360.1213' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("360.1213");
        assert.isFalse(rezult);
    });

    it("'60 12 13' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("60 12 13");
        assert.isFalse(rezult);
    });
    
    it("'#34.1234' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("#34.1234");
        assert.isFalse(rezult);
    });

    it("'36.12134' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("36.12134");
        assert.isFalse(rezult);
    });

    it("'/.1213' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("/.1213");
        assert.isFalse(rezult);
    });

    it("'sdf' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("sdf");
        assert.isFalse(rezult);
    });

    it("'0' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("0");
        assert.isFalse(rezult);
    });

    it("'90' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle("90");
        assert.isFalse(rezult);
    });

    it("'.3030' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle(".3030");
        assert.isFalse(rezult);
    });

    it("'56.6030' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle(".3030");
        assert.isFalse(rezult);
    });

    it("'65.3061' is not valid horizontal angle", function() {
        let rezult = isValidHorizontalAngle(".3030");
        assert.isFalse(rezult);
    });

});

/**
 * Tests isValidTiltAngle
 */
describe("isValidTiltAngle", function() {

    it("'0.0000' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("0.0000");
        assert.isTrue(rezult);
    });

    it("'1.0001' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("1.0001");
        assert.isTrue(rezult);
    });

    it("'-1.0001' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("-1.0001");
        assert.isTrue(rezult);
    });

    it("'89.5959' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("89.5959");
        assert.isTrue(rezult);
    });

    it("'-89.5959' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("-89.5959");
        assert.isTrue(rezult);
    });


    it("'10.1545' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("10.1545");
        assert.isTrue(rezult);
    });

    it("'-0.0000' is valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("-0.0000");
        assert.isTrue(rezult);
    });

    it("'0 00 00' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("0 00 00");
        assert.isFalse(rezult);
    });

    it("'90.0000' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("90.0000");
        assert.isFalse(rezult);
    });

    it("'-90.0000' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("-90.0000");
        assert.isFalse(rezult);
    });

    it("'359.5959' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("359.5959");
        assert.isFalse(rezult);
    });

    it("'0.6159' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("0.6159");
        assert.isFalse(rezult);
    });

    it("'0.01560' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("0.01560");
        assert.isFalse(rezult);
    });

    it("'O.01560' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("O.01560");
        assert.isFalse(rezult);
    });

    it("'#.01560' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("#.01560");
        assert.isFalse(rezult);
    });

    it("'0.015597' is not valid value of tilt angle", function() {
        let rezult = isValidTiltAngle("0.015597");
        assert.isFalse(rezult);
    });


});