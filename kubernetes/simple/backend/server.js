"use strict";

const express = require("express");

const app = express();

app.get("/*", (req, res) => {
  res.set("Access-Control-Allow-Origin", "*");

  res.json({ message: "Hello world *again* !?!" });
});

app.listen(3000);
