const express = require("express");
const UsageHistory = require("./generators/UsageHistory");
const PhysicalCondition = require("./generators/PhysicalCondition");
const AuthentificityPhone = require("./generators/AuthentificityPhone");

const app = express();

const mockData = (req, res, generator) => {
    const count = req.query.count || 3;
    const data = [];
    for(let i = 0; i < count; i++){
        data.push(generator());
    }
    res.json(data);
} 

app.get("/api/history-usage/:imei", (req, res) => {
    res.json(UsageHistory(req.params.imei))
});

app.get("/api/physical-condition/:imei", (req, res) => {
    res.json(PhysicalCondition(req.params.imei));
});

app.get("/api/authenticity-phone/:imei", (req, res) => {
    res.json(AuthentificityPhone(req.params.imei));
});

app.listen(8082, () => console.log("API sever running")) ;