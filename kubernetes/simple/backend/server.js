"use strict";

const express = require("express");

const app = express();

app.get("/*", (req, res) => {
  console.log("Incoming call from " + req.path);

  res.set("Access-Control-Allow-Origin", "*");

  res.json({ message: "Hello world *again* !?!" });
});

console.log("Listening on port " + 3000);
app.listen(3000);
