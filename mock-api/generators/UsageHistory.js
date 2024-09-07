const chance = require("chance").Chance();

module.exports = (imei) => {
  switch(imei){
    case "123": return {
      "imei": imei,
      "usageHistories": [
        {
          "timeStamp": chance.date(),
          "event": "Operating System Update",
          "details": "Updated to iOS 15.1"
        },
        {
          "timeStamp": chance.date(),
          "event": "Network Unlock",
          "details": "True"
        },
        {
          "timeStamp": chance.date(),
          "event": "Jail Break",
          "details": "Pending investigate"
        }
      ]
    }
    case "70a25cd6-7c5d-4a75-afd2-2a180752e749": return {
      "imei": imei,
      "usageHistories": [
        {
          "timeStamp": chance.date(),
          "event": "Operating System Update",
          "details": "Updated to iOS 7"
        },
        {
          "timeStamp": chance.date(),
          "event": "Network Unlock",
          "details": "False"
        },
        {
          "timeStamp": chance.date(),
          "event": "Jail Break",
          "details": "True"
        },
        {
          "timeStamp": chance.date(),
          "event": "Jail Break",
          "details": "Pending investigate"
        }
      ]
    }
  } 
};

