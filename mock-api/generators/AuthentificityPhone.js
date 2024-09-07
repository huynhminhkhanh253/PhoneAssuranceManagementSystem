const chance = require("chance").Chance();

module.exports = (imei) => {
  switch(imei){
    case "123": return {
      "imei": imei,
      "authenticity": {
        "imeiStatus": "Not Blacklisted",
        "ownershipStatus": "Valid",
        "securityFeatures": {
          "faceIdStatus": "Operational",
          "touchIdStatus": "Operational",
          "passcodeLockStatus": "Enabled"
        },
        "warrantyInformation": {
          "warrantyStatus": "Active",
          "warrantyExpiration": "2023-01-01",
          "serviceHistory": [
            {
              "timeStamp": "2022-06-15T10:00:00",
              "serviceType": "Screen Replacement",
              "details": "Replaced cracked screen"
            },
            {
              "timeStamp": "2022-09-20T14:30:00",
              "serviceType": "Battery Replacement",
              "details": "Upgraded to a new battery"
            }
          ]
        }
      }
    }
    case "70a25cd6-7c5d-4a75-afd2-2a180752e749": return {
      "imei": imei,
      "authenticity": {
        "imeiStatus": "Pending",
        "ownershipStatus": "Valid",
        "securityFeatures": {
          "faceIdStatus": "Operational",
          "touchIdStatus": "Operational",
          "passcodeLockStatus": "Enabled"
        },
        "warrantyInformation": {
          "warrantyStatus": "Active",
          "warrantyExpiration": "2023-01-01",
          "serviceHistory": [
            {
              "timeStamp": "2022-06-15T10:00:00",
              "serviceType": "Screen Replacement",
              "details": "Replaced cracked screen"
            },
            {
              "timeStamp": "2022-09-20T14:30:00",
              "serviceType": "Battery Replacement",
              "details": "Upgraded to a new battery"
            }
          ]
        }
      }
    }
  }       
};