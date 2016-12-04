const GameOfLife = require('../app/GameOfLife');
const assert = require('assert');

describe('GameOfLife:', () => {
  it('should exist', () => {
     assert(GameOfLife.tick());
  });
});