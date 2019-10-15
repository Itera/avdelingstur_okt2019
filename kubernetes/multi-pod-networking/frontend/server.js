"use strict";

const express = require("express");
const request = require("request");

const app = express();

app.get("/*", (req, res) => {
  console.log("Incoming call from " + req.path);

  request(
    {
      uri: "http://multi-service-backend:8081/"
    },
    function(error, response, body) {
      console.log("Response code " + response.statusCode);

      if (!error && response.statusCode === 200) {
        res.json(JSON.parse(body));
      } else {
        console.log("Error: " + error);

        res.send(error);
      }
    }
  );
});

console.log("Listening on port " + 80);
app.listen(80);
