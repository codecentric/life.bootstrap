package de.codecentric.life;

public class GameOfLife {
  private int generations = 0;

  public String tick() {
    generations++;
    return "Generation: " + generations;
  }
}
