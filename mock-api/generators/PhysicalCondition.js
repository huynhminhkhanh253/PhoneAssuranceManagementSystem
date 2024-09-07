const chance = require("chance").Chance();

module.exports = (imei) => {
	switch(imei){
		case "70a25cd6-7c5d-4a75-afd2-2a180752e749": return {
			"imei": imei,
			"physicalCondition": {
			"screenHealth": "Bad",
			"batteryHealth": "50%",
			"externalDamage": true,
			"cameraFunctionality": "Operational",
			"speakerMicrophoneHealth": "Good",
			"sensorHealth": "Operational",
			"dimensions": {"height": 150, "width": 70, "depth": 7},
			"weight": 155
			}
		}
		case "456": return {
			"imei": imei,
			"physicalCondition": {
			"screenHealth": "Good",
			"batteryHealth": "80%",
			"externalDamage": false,
			"cameraFunctionality": "Operational",
			"speakerMicrophoneHealth": "Good",
			"sensorHealth": "Operational",
			"dimensions": {"height": 150, "width": 70, "depth": 7},
			"weight": 155
			}
		}
	}
    
};