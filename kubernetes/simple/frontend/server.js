"use strict";

const express = require("express");
const router = express.Router();
const request = require("request");

const app = express();

app.get("/*", (req, res) => {
  console.log("Incoming call from " + req.path);

  request(
    {
      uri: "http://localhost:3000/"
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
