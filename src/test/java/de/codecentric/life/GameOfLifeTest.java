package de.codecentric.life;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GameOfLifeTest {

  private GameOfLife life;

  @Before
  public void setUp() throws Exception {
    life = new GameOfLife();
  }

  @Test
  public void shouldIncreaseGenerationCountForEachTick() throws Exception {
    assertThat(life.tick(), is(equalTo("Generation: 1")));
    assertThat(life.tick(), is(equalTo("Generation: 2")));
  }

  @After
  public void tearDown() throws Exception {
    life = null;
  }
}