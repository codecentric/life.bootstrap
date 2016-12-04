const express = require('express');
const GameOfLife = require('./app/GameOfLife');
const app = express();

app.get('/life', [tick, response]);
app.listen('6102');

function tick(req, res, next) {
  req.result = GameOfLife.tick();
  next();
}

function response(req, res, next) {
  res.set('Refresh', '1');
  res.type('application/json').status(200).json(req.result).end();
}