"use strict";

const express = require("express");
const router = express.Router();
const request = require("request");

const app = express();

app.get("/*", (req, res) => {
  request(
    {
      uri: "http://localhost:3000/"
    },
    function(error, response, body) {
      console.log(error);
      console.log(body);
      if (!error && response.statusCode === 200) {
        res.json(body);
      } else {
        res.json(error);
      }
    }
  );
});

app.listen(80);
