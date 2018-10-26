
package asteroids;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part3.facade.IFacade;
import asteroids.program.Program;
import asteroids.program.ProgramFactory;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

public class Tests {

  private static final double EPSILON = 0.0001;
  private static final double BIG_EPSILON = 1.0E14;
  private static final double VERY_BIG_EPSILON = 1.0E34;

  static int nbStudentsInTeam;
  IFacade facade;
  IProgramFactory<?, ?, ?, Program> programFactory = new ProgramFactory();
  World filledWorld;
  Ship ship1, ship2, ship3;
  Bullet bullet1;
  static int score = 0;
  static int max_score = 0;

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println("Score: " + score + "/" + max_score);
  }

  @Before
  public void setUp() throws ModelException {
    facade = new asteroids.facade.Facade();
    nbStudentsInTeam = facade.getNbStudentsInTeam();
    filledWorld = facade.createWorld(2000, 2000);
    ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    for (int i = 1; i < 10; i++) {
      Bullet bulletToLoad = facade.createBullet(100, 120, 0, 0, 10);
      facade.loadBulletOnShip(ship1, bulletToLoad);
    }
    facade.addShipToWorld(filledWorld, ship1);
    ship2 = facade.createShip(200, 220, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(filledWorld, ship2);
    bullet1 = facade.createBullet(300, 320, 10, 5, 50);
    facade.addBulletToWorld(filledWorld, bullet1);
  }

  @Test
  public void testCreateShip() throws ModelException {
    max_score += 12;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    assertEquals(100, facade.getShipPosition(ship)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship)[0], EPSILON);
    assertEquals(5, facade.getShipVelocity(ship)[1], EPSILON);
    assertEquals(50, facade.getShipRadius(ship), EPSILON);
    assertTrue(facade.getShipOrientation(ship) >= 0.0 - EPSILON);
    assertTrue(facade.getShipOrientation(ship) <= 2.0 * Math.PI + EPSILON);
    assertEquals(1.0E20, facade.getShipMass(ship), BIG_EPSILON);
    assertEquals(null, facade.getShipWorld(ship));
    assertFalse(facade.isShipThrusterActive(ship));
    assertEquals(0, facade.getShipAcceleration(ship), EPSILON);
    assertEquals(0, facade.getNbBulletsOnShip(ship));
    assertNull(facade.getShipProgram(ship));
    assertFalse(facade.isTerminatedShip(ship));
    score += 12;
  }

  @Test
  public void testCreateShipPositionNan() throws ModelException {
    try {
      max_score += 2;
      facade.createShip(Double.NaN, 120, 10, 5, 50, 0, 1.0E20);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testCreateShipNegativePositionNotInWorld() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(-100, 120, 10, 5, 50, 0, 1.0E20);
    assertEquals(-100, facade.getShipPosition(ship)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship)[1], EPSILON);
    score += 2;
  }

  @Test
  public void testCreateShipInfinitePositionNotInWorld() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, 5, 50, 0, 1.0E20);
    assertTrue(Double.isInfinite(facade.getShipPosition(ship)[0]));
    assertTrue(Double.isInfinite(facade.getShipPosition(ship)[1]));
    score += 2;
  }

  @Test
  public void testCreateShipVelocityNan() throws ModelException {
    max_score += 1;
    Ship ship = facade.createShip(100, 120, Double.NaN, 5, 50, 0, 1.0E20);
    double velocity = Math.sqrt(facade.getShipVelocity(ship)[0] * facade.getShipVelocity(ship)[0]
        + facade.getShipVelocity(ship)[1] * facade.getShipVelocity(ship)[1]);
    assertTrue(0 - EPSILON <= velocity);
    assertTrue(velocity <= 300000 + EPSILON);
    score += 1;
  }

  @Test
  public void testCreateShipVelocityLargerThanLightSpeed() throws ModelException {
    max_score += 3;
    Ship ship = facade.createShip(100, 120, 500000, 0, 50, 0, 1.0E20);
    double velocity = Math.sqrt(facade.getShipVelocity(ship)[0] * facade.getShipVelocity(ship)[0]
        + facade.getShipVelocity(ship)[1] * facade.getShipVelocity(ship)[1]);
    assertTrue(0 - EPSILON <= velocity);
    assertTrue(velocity <= 300000 + EPSILON);
    score += 3;
  }

  public void testCreateShipRadiusNan() throws ModelException {
    try {
      max_score += 1;
      facade.createShip(100, 120, 10, 5, Double.NaN, 0, 1.0E20);
      fail();
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testCreateShipRadiusZero() throws ModelException {
    try {
      max_score += 2;
      facade.createShip(100, 120, 10, 5, 0, 0, 1.0E20);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testCreateShipDirectionNegative() throws ModelException {
    try {
      max_score += 1;
      facade.createShip(100, 120, 10, 5, 50, -Math.PI, 1.0E20);
      fail();
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testCreateShipDirectionTooLarge() throws ModelException {
    try {
      max_score += 1;
      facade.createShip(100, 120, 10, 5, 50, 3 * Math.PI, 1.0E20);
      fail();
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testCreateShipMassNan() throws ModelException {
    max_score += 1;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, Double.NaN);
    double minimalMass = Math.PI * 4 / 3. * Math.pow(50, 3) * 1.42E12;
    assertTrue(Double.isFinite(facade.getShipMass(ship)));
    assertTrue(minimalMass - BIG_EPSILON <= facade.getShipMass(ship));
    score += 1;
  }

  @Test
  public void testCreateShipMassNegative() throws ModelException {
    max_score += 1;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, -4);
    double minimalMass = Math.PI * 4 / 3. * Math.pow(50, 3) * 1.42E12;
    assertTrue(Double.isFinite(facade.getShipMass(ship)));
    assertTrue(minimalMass - BIG_EPSILON <= facade.getShipMass(ship));
    score += 1;
  }

  @Test
  public void testCreateShipMassTooSmall() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0);
    double minimalMass = Math.PI * 4 / 3. * Math.pow(50, 3) * 1.42E12;
    assertTrue(Double.isFinite(facade.getShipMass(ship)));
    assertTrue(minimalMass - BIG_EPSILON <= facade.getShipMass(ship));
    score += 2;
  }

  @Test
  public void testSetShipThruster() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E21);
    facade.setThrusterActive(ship, true);
    double expectedAcceleration = 1.5E20 / 1.0E21;
    assertTrue(facade.isShipThrusterActive(ship));
    assertEquals(expectedAcceleration, facade.getShipAcceleration(ship), EPSILON);
    score += 2;
  }

  @Test
  public void testLoadProgram() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    String code = "print 4.0;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship, program);
    assertEquals(program, facade.getShipProgram(ship));
    score += 2;
  }

  @Test
  public void testCreateBullet() throws ModelException {
    max_score += 10;
    Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
    assertEquals(100, facade.getBulletPosition(bullet)[0], EPSILON);
    assertEquals(120, facade.getBulletPosition(bullet)[1], EPSILON);
    assertEquals(10, facade.getBulletVelocity(bullet)[0], EPSILON);
    assertEquals(5, facade.getBulletVelocity(bullet)[1], EPSILON);
    assertEquals(50, facade.getBulletRadius(bullet), EPSILON);
    assertEquals(4.0 * 7.8E12 * Math.PI * Math.pow(50.0, 3) / 3.0, facade.getBulletMass(bullet), BIG_EPSILON);
    assertNull(facade.getBulletWorld(bullet));
    assertNull(facade.getBulletShip(bullet));
    assertNull(facade.getBulletSource(bullet));
    score += 10;
  }

  @Test
  public void testCreateAsteroid() throws ModelException {
    max_score += 6;
    Asteroid asteroid = facade.createAsteroid(100, 120, 10, 5, 50);
    assertEquals(100, facade.getAsteroidPosition(asteroid)[0], EPSILON);
    assertEquals(120, facade.getAsteroidPosition(asteroid)[1], EPSILON);
    assertEquals(10, facade.getAsteroidVelocity(asteroid)[0], EPSILON);
    assertEquals(5, facade.getAsteroidVelocity(asteroid)[1], EPSILON);
    assertEquals(50, facade.getAsteroidRadius(asteroid), EPSILON);
    assertEquals(4.0 * 2.65E12 * Math.PI * Math.pow(50.0, 3) / 3.0, facade.getAsteroidMass(asteroid), BIG_EPSILON);
    assertNull(facade.getAsteroidWorld(asteroid));
    score += 6;
  }

  @Test
  public void testCreateAsteroid_SmallRadius() throws ModelException {
    max_score += 4;
    Asteroid asteroid = facade.createAsteroid(100, 120, 10, 5, 6);
    assertEquals(6, facade.getAsteroidRadius(asteroid), EPSILON);
    score += 4;
  }

  @Test
  public void testCreatePlanetoid() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 10;
      Planetoid planetoid = facade.createPlanetoid(100, 120, 10, 5, 50, 1000);
      assertEquals(100, facade.getPlanetoidPosition(planetoid)[0], EPSILON);
      assertEquals(120, facade.getPlanetoidPosition(planetoid)[1], EPSILON);
      assertEquals(10, facade.getPlanetoidVelocity(planetoid)[0], EPSILON);
      assertEquals(5, facade.getPlanetoidVelocity(planetoid)[1], EPSILON);
      assertEquals(50 - 1000 * 0.000001, facade.getPlanetoidRadius(planetoid), EPSILON);
      assertEquals(4.0 * 0.917E12 * Math.PI * Math.pow(facade.getPlanetoidRadius(planetoid), 3) / 3.0,
          facade.getPlanetoidMass(planetoid), BIG_EPSILON);
      assertEquals(1000, facade.getPlanetoidTotalTraveledDistance(planetoid), EPSILON);
      assertNull(facade.getPlanetoidWorld(planetoid));
      score += 10;
    }
  }

  @Test
  public void testGetRadiusPlanetoid() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 10;
      World world = facade.createWorld(500000000, 500000000);
      Planetoid planetoid = facade.createPlanetoid(100, 120, 1000, 0, 50, 0);
      facade.addPlanetoidToWorld(world, planetoid);
      facade.evolve(world, 50, null);
      assertEquals(50 - 1000 * 50 * 0.000001, facade.getPlanetoidRadius(planetoid), EPSILON);
      score += 10;
    }
  }

  @Test
  public void testCreatePlanetoidTotalTraveledDistanceTooHigh() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 7;
      Planetoid planetoid = facade.createPlanetoid(100, 120, 10, 5, 5, 1000);
      assertTrue(facade.isTerminatedPlanetoid(planetoid));
      score += 7;
    }
  }

  @Test
  public void testCreateWorld() throws ModelException {
    max_score += 4;
    World world = facade.createWorld(1000, 800);
    assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
    assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
    assertTrue(facade.getWorldShips(world).isEmpty());
    assertTrue(facade.getWorldBullets(world).isEmpty());
    score += 4;
  }

  @Test
  public void testCreateWorldNegativeSize() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(1000, -800);
    double width = facade.getWorldSize(world)[0];
    double height = facade.getWorldSize(world)[1];
    assertTrue(Double.isFinite(width) && 0.0 - EPSILON <= width);
    assertTrue(Double.isFinite(height) && 0.0 - EPSILON <= height);
    score += 1;
  }

  @Test
  public void testCreateWorldNanSize() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(Double.NaN, 800);
    double width = facade.getWorldSize(world)[0];
    double height = facade.getWorldSize(world)[1];
    assertTrue(Double.isFinite(width) && 0.0 - EPSILON <= width);
    assertTrue(Double.isFinite(height) && 0.0 - EPSILON <= height);
    score += 1;
  }

  @Test
  public void testCreateWorldInfiniteSize() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(Double.POSITIVE_INFINITY, 800);
    double width = facade.getWorldSize(world)[0];
    double height = facade.getWorldSize(world)[1];
    assertTrue(Double.isFinite(width) && (0.0 - EPSILON <= width));
    assertTrue(Double.isFinite(height) && (0.0 - EPSILON <= height));
    score += 1;
  }

  @Test
  public void testAddShip() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    assertEquals(1, facade.getWorldShips(world).size());
    assertTrue(facade.getWorldShips(world).contains(ship));
    assertEquals(world, facade.getShipWorld(ship));
    score += 6;
  }

  @Test
  public void testAddShipNull() throws ModelException {
    try {
      max_score += 1;
      World world = facade.createWorld(1000, 1000);
      facade.addShipToWorld(world, null);
      fail();
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testAddShipOutOfWorld() throws ModelException {
    try {
      max_score += 4;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100000, 1200000, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship);
      fail();
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testAddOverlappingShips() throws ModelException {
    try {
      max_score += 3;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(130, 150, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testAddShipTwice() throws ModelException {
    try {
      max_score += 1;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship);
      facade.addShipToWorld(world, ship);
      fail();
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testAddShipInDifferentWorld() throws ModelException {
    try {
      max_score += 3;
      World world = facade.createWorld(1000, 1000);
      World otherWorld = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(otherWorld, ship);
      facade.addShipToWorld(world, ship);
      assertTrue(facade.getWorldShips(world).contains(ship) != facade.getWorldShips(otherWorld).contains(ship));
      assertTrue(facade.getWorldShips(world).size() + facade.getWorldShips(otherWorld).size() == 1);
      score += 3;
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testAddBullet() throws ModelException {
    max_score += 3;
    World world = facade.createWorld(1000, 1000);
    Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
    facade.addBulletToWorld(world, bullet);
    assertEquals(1, facade.getWorldBullets(world).size());
    assertTrue(facade.getWorldBullets(world).contains(bullet));
    assertEquals(world, facade.getBulletWorld(bullet));
    score += 3;
  }

  @Test
  public void testAddOverlappingBulletShip() throws ModelException {
    try {
      max_score += 4;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Bullet bullet = facade.createBullet(130, 150, 10, 5, 50);
      facade.addShipToWorld(world, ship);
      facade.addBulletToWorld(world, bullet);
      fail();
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testRemoveShip() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.removeShipFromWorld(world, ship1);
    assertEquals(1, facade.getWorldShips(world).size());
    assertTrue(facade.getWorldShips(world).contains(ship2));
    assertEquals(null, facade.getShipWorld(ship1));
    assertEquals(world, facade.getShipWorld(ship2));
    score += 6;
  }

  @Test
  public void testRemoveShipNull() throws ModelException {
    try {
      max_score += 1;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      facade.removeShipFromWorld(world, null);
      fail();
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testRemoveShipNotInWorld() throws ModelException {
    try {
      max_score += 2;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.removeShipFromWorld(world, ship2);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testRemoveShipInDifferentWorld() throws ModelException {
    try {
      max_score += 3;
      World world = facade.createWorld(1000, 1000);
      World otherWorld = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(otherWorld, ship2);
      facade.removeShipFromWorld(world, ship2);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testRemoveBullet() throws ModelException {
    max_score += 3;
    World world = facade.createWorld(1000, 1000);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(400, 520, 10, 5, 50);
    facade.addBulletToWorld(world, bullet1);
    facade.addBulletToWorld(world, bullet2);
    facade.removeBulletFromWorld(world, bullet1);
    assertEquals(1, facade.getWorldBullets(world).size());
    assertTrue(facade.getWorldBullets(world).contains(bullet2));
    assertEquals(null, facade.getBulletWorld(bullet1));
    assertEquals(world, facade.getBulletWorld(bullet2));
    score += 3;
  }

  @Test
  public void testGetEntityAt() throws ModelException {
    max_score += 2;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Object result = facade.getEntityAt(world, 100, 120);
    assertEquals(ship, result);
    score += 2;
  }

  @Test
  public void testGetEntityAt_NoEntity() throws ModelException {
    max_score += 2;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Object result = facade.getEntityAt(world, 110, 120);
    assertNull(result);
    score += 2;
  }

  @Test
  public void testGetEntityAt_PositionOutOfBounds() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Object result = facade.getEntityAt(world, Double.POSITIVE_INFINITY, 120);
    assertNull(result);
    score += 1;
  }

  @Test
  public void testGetEntities() throws ModelException {
    max_score += 6;
    Set<? extends Object> entities = facade.getEntities(filledWorld);
    assertEquals(3, entities.size());
    assertTrue(entities.contains(ship1));
    assertTrue(entities.contains(ship2));
    assertTrue(entities.contains(bullet1));
    Ship otherShip = facade.createShip(400, 420, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(filledWorld, otherShip);
    assertEquals(3, entities.size());
    score += 6;
  }

  @Test
  public void testGetShips() throws ModelException {
    max_score += 2;
    Set<? extends Object> entities = facade.getWorldShips(filledWorld);
    assertEquals(2, entities.size());
    assertTrue(entities.contains(ship1));
    assertTrue(entities.contains(ship2));
    score += 2;
  }

  @Test
  public void testGetBullets() throws ModelException {
    max_score += 2;
    Bullet bullet1 = facade.createBullet(500, 520, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(600, 620, 10, 5, 50);
    facade.addBulletToWorld(filledWorld, bullet1);
    facade.addBulletToWorld(filledWorld, bullet2);
    Set<? extends Bullet> bullets = facade.getWorldBullets(filledWorld);
    assertEquals(3, bullets.size());
    assertTrue(bullets.contains(bullet1));
    assertTrue(bullets.contains(bullet2));
    score += 2;
  }

  @Test
  public void testLoadBulletOnShip() throws ModelException {
    max_score += 8;
    Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E40);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(200, 10, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    assertEquals(2, facade.getNbBulletsOnShip(ship));
    if (nbStudentsInTeam > 1) {
      assertEquals(1.0E40 + facade.getBulletMass(bullet1) + facade.getBulletMass(bullet2), facade.getShipMass(ship),
          VERY_BIG_EPSILON);
      assertEquals(2, facade.getBulletsOnShip(ship).size());
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet1));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet2));
      assertEquals(ship, facade.getBulletShip(bullet1));
      assertNull(facade.getBulletWorld(bullet1));
    } else {
      Bullet dummyBullet = facade.createBullet(0, 0, 0, 0, 3);
      assertEquals(1.0E40 + 2 * facade.getBulletMass(dummyBullet), facade.getShipMass(ship), VERY_BIG_EPSILON);
      assertTrue(facade.isTerminatedBullet(bullet1));
      assertTrue(facade.isTerminatedBullet(bullet2));
    }
    score += 8;
  }

  @Test
  public void testLoadBulletOnShipOutOfShip() throws ModelException {
    try {
      max_score += 5;
      Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Bullet bullet = facade.createBullet(560, 120, 10, 5, 50);
      facade.loadBulletOnShip(ship, bullet);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testLoadBulletOnShipOverlappingBullets() throws ModelException {
    max_score += 4;
    Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    assertEquals(2, facade.getNbBulletsOnShip(ship));
    score += 4;
  }

  @Test
  public void testLoadBulletOnShipBulletAlreadyInWorld() throws ModelException {
    try {
      max_score += 5;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
      facade.addBulletToWorld(world, bullet);
      facade.loadBulletOnShip(ship, bullet);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testLoadMultipleBulletsOnShip() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 7;
      Ship ship = facade.createShip(600, 620, 10, 5, 500, 0, 1.0E20);
      Set<Bullet> bullets = new HashSet<>();
      Bullet bullet1 = facade.createBullet(600, 620, 10, 5, 50);
      Bullet bullet2 = facade.createBullet(700, 510, 10, 5, 30);
      Bullet bullet3 = facade.createBullet(500, 500, 0, 0, 20);
      bullets.add(bullet1);
      bullets.add(bullet2);
      bullets.add(bullet3);
      facade.loadBulletsOnShip(ship, bullets);
      assertEquals(3, facade.getNbBulletsOnShip(ship));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet1));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet2));
      assertEquals(ship, facade.getBulletShip(bullet1));
      assertNull(facade.getBulletWorld(bullet1));
      score += 7;
    }
  }

  @Test
  public void testLoadMultipleBulletsOnShipIllegalBullets() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 7;
      Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Set<Bullet> bullets = new HashSet<>();
      Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
      Bullet bullet2 = facade.createBullet(200, 10, 10, 5, 30);
      bullets.add(bullet1);
      bullets.add(bullet2);
      bullets.add(null);
      try {
        facade.loadBulletsOnShip(ship, bullets);
        fail();
      } catch (ModelException exc) {
      }
      assertEquals(0, facade.getNbBulletsOnShip(ship));
      score += 7;
    }
  }

  @Test
  public void testRemoveBulletFromShip() throws ModelException {
    max_score += 8;
    Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E40);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(200, 10, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.removeBulletFromShip(ship, bullet1);
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    if (nbStudentsInTeam > 1) {
      assertEquals(1.0E40 + facade.getBulletMass(bullet2), facade.getShipMass(ship), VERY_BIG_EPSILON);
      assertEquals(1, facade.getBulletsOnShip(ship).size());
      assertFalse(facade.getBulletsOnShip(ship).contains(bullet1));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet2));
    } else {
      Bullet dummyBullet = facade.createBullet(0, 0, 0, 0, 3);
      assertEquals(1.0E40 + facade.getBulletMass(dummyBullet), facade.getShipMass(ship), VERY_BIG_EPSILON);
    }
    score += 8;
  }

  @Test
  public void testRemoveBulletFromShipBulletOnOtherShip() throws ModelException {
    try {
      max_score += 6;
      Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Ship otherShip = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
      Bullet bullet2 = facade.createBullet(300, 220, 10, 5, 50);
      facade.loadBulletOnShip(ship, bullet1);
      facade.loadBulletOnShip(otherShip, bullet2);
      facade.removeBulletFromShip(ship, bullet2);
      if (nbStudentsInTeam == 1) {
        assertEquals(0, facade.getNbBulletsOnShip(ship));
        throw new ModelException("Needed for a succesfull test.");
      }
      fail();
    } catch (ModelException exc) {
      score += 6;
    }
  }

  @Test
  public void testFireBullet() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 10);
    Bullet bullet2 = facade.createBullet(480, 300, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.fireBullet(ship);
    Bullet usedBullet;
    if (nbStudentsInTeam <= 1)
      usedBullet = facade.getWorldBullets(world).iterator().next();
    else if (facade.getBulletsOnShip(ship).contains(bullet1))
      usedBullet = bullet2;
    else
      usedBullet = bullet1;
    assertEquals(1, facade.getWorldBullets(world).size());
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    assertEquals(650 + facade.getBulletRadius(usedBullet), facade.getBulletPosition(usedBullet)[0], 10.0);
    assertEquals(200, facade.getBulletPosition(usedBullet)[1], 10.0);
    assertEquals(250, facade.getBulletVelocity(usedBullet)[0], EPSILON);
    assertEquals(0, facade.getBulletVelocity(usedBullet)[1], EPSILON);
    assertEquals(ship, facade.getBulletSource(usedBullet));
    score += 10;
  }

  @Test
  public void testFireBulletShipNotInWorld() throws ModelException {
    max_score += 3;
    Ship ship = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 10);
    Bullet bullet2 = facade.createBullet(480, 300, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.fireBullet(ship);
    assertEquals(2, facade.getNbBulletsOnShip(ship));
    score += 3;
  }

  @Test
  public void testFireBulletNoBullets() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    facade.fireBullet(ship);
    assertEquals(0, facade.getNbBulletsOnShip(ship));
    score += 2;
  }

  @Test
  public void testFireBulletOutOfBounds() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(550, 550, 0, 0, 547, 3 * Math.PI / 2, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 3);
    Bullet bullet2 = facade.createBullet(480, 300, 10, 5, 3);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.fireBullet(ship);
    Bullet usedBullet = null;
    if (nbStudentsInTeam > 1)
      if (facade.getBulletsOnShip(ship).contains(bullet1))
        usedBullet = bullet2;
      else
        usedBullet = bullet1;
    assertTrue(facade.getWorldBullets(world).isEmpty());
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    assertTrue(nbStudentsInTeam <= 1 || facade.isTerminatedBullet(usedBullet));
    score += 8;
  }

  @Test
  public void testFireBulletImmediateHit() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    Ship ship2 = facade.createShip(700, 200, 0, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship2);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 10);
    facade.loadBulletOnShip(ship1, bullet1);
    facade.fireBullet(ship1);
    assertTrue(facade.getWorldBullets(world).isEmpty());
    assertTrue(facade.getWorldShips(world).contains(ship1));
    assertFalse(facade.getWorldShips(world).contains(ship2));
    assertTrue(facade.isTerminatedShip(ship2));
    assertTrue(facade.isTerminatedBullet(bullet1));
    assertEquals(ship1, facade.getBulletSource(bullet1));
    score += 8;
  }

  @Test
  public void testBoundaryCollision_FiniteTimeRightCollision() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 300, 100, 0, 100, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    double expectedTime = (5000.0 - 600.0) / 100.0;
    assertEquals(expectedTime, timeToCollision, EPSILON);
    double[] collisionPosition = facade.getPositionCollisionBoundary(ship);
    assertEquals(5000, collisionPosition[0], EPSILON);
    assertEquals(300, collisionPosition[1], EPSILON);
    score += 6;
  }

  @Test
  public void testBoundaryCollision_FiniteTimeTopCollision() throws ModelException {
    max_score += 4;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 300, 0, 100, 100, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    double expectedTime = (5000.0 - 300.0 - 100.0) / 100.0;
    assertEquals(expectedTime, timeToCollision, EPSILON);
    double[] collisionPosition = facade.getPositionCollisionBoundary(ship);
    assertEquals(500, collisionPosition[0], EPSILON);
    assertEquals(5000, collisionPosition[1], EPSILON);
    score += 4;
  }

  @Test
  public void testBoundaryCollision_NoVelocity() throws ModelException {
    max_score += 5;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 300, 0, 0, 100, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    assertEquals(Double.POSITIVE_INFINITY, timeToCollision, EPSILON);
    assertNull(facade.getPositionCollisionBoundary(ship));
    score += 5;
  }

  @Test
  public void testBoundaryCollision_NoWorld() throws ModelException {
    max_score += 3;
    Ship ship = facade.createShip(500, 100, 0, 0, 100, 0, 1.0E20);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    assertEquals(Double.POSITIVE_INFINITY, timeToCollision, EPSILON);
    assertNull(facade.getPositionCollisionBoundary(ship));
    score += 3;
  }

  @Test
  public void testEntityCollisionHorizontalMovement() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200, 100, -10, 0, 30, 0, 0.1E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(2.5, dt, 0.1);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNotNull(pos);
    assertEquals(2, pos.length);
    assertEquals(145, pos[0], EPSILON);
    assertEquals(100, pos[1], EPSILON);
    score += 12;
  }

  @Test
  public void testEntityCollisionSlopingMovement() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100.0 / Math.sqrt(2.0), 100.0 / Math.sqrt(2.0), 10.0 / Math.sqrt(2.0),
        10 / Math.sqrt(2.0), 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200.0 / Math.sqrt(2.0), 200.0 / Math.sqrt(2.0), -10 / Math.sqrt(2.0),
        -10 / Math.sqrt(2.0), 30, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(2.5, dt, 0.1);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNotNull(pos);
    assertEquals(2, pos.length);
    assertEquals(145.0 / Math.sqrt(2.0), pos[0], EPSILON);
    assertEquals(145.0 / Math.sqrt(2.0), pos[1], EPSILON);
    score += 10;
  }

  @Test
  public void tesEntityCollisionSameVelocity() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200, 100, 10, 0, 30, 0, 0.1E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(Double.POSITIVE_INFINITY, dt, EPSILON);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNull(pos);
    score += 8;
  }

  @Test
  public void tesEntityCollisionSameEntities() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(Double.POSITIVE_INFINITY, dt, EPSILON);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNull(pos);
    score += 8;
  }

  @Test
  public void testEntityCollisionDifferentHolders() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    Bullet bullet1 = facade.createBullet(300, 100, -10, 0, 20);
    Bullet bullet2 = facade.createBullet(100, 100, -10, 0, 5);
    facade.addBulletToWorld(world, bullet1);
    facade.loadBulletOnShip(ship1, bullet2);
    double dt = facade.getTimeCollisionEntity(bullet1, bullet2);
    assertEquals(Double.POSITIVE_INFINITY, dt, EPSILON);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNull(pos);
    score += 10;
  }

  @Test
  public void testNextCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200, 100, -10, 0, 30, 0, 1.0E20);
    Ship ship3 = facade.createShip(100, 300, 3, 3, 20, 0, 1.0E20);
    Ship ship4 = facade.createShip(100, 50, 0, -50, 25, 0, 1.0E22);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.addShipToWorld(world, ship3);
    facade.addShipToWorld(world, ship4);
    double timeToCollision = facade.getTimeNextCollision(world);
    double[] positionCollision = facade.getPositionNextCollision(world);
    assertEquals(0.5, timeToCollision, EPSILON);
    assertEquals(100, positionCollision[0], EPSILON);
    assertEquals(0, positionCollision[1], EPSILON);
    score += 12;
  }

  @Test
  public void testEvolveEmptyWorld() throws ModelException {
    max_score += 2;
    World world = facade.createWorld(1000, 1000);
    facade.evolve(world, 30, null);
    assertEquals(0, facade.getWorldShips(world).size());
    score += 2;
  }

  @Test
  public void testEvolveDtNan() throws ModelException {
    try {
      max_score += 2;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 120, 0, -5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      facade.evolve(world, Double.NaN, null);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testEvolveDtNegative() throws ModelException {
    try {
      max_score += 2;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 120, 0, -5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      facade.evolve(world, -1, null);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testEvolveVelocitiesZero() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, 0, 0, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(900, 120, 0, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 55, null);
    assertEquals(2, facade.getWorldShips(world).size());
    assertEquals(100, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    assertEquals(900, facade.getShipPosition(ship2)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship2)[1], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    score += 6;
  }

  @Test
  public void testEvolveNoCollisions() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(400, 120, 0, -5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 1, null);
    assertEquals(2, facade.getWorldShips(world).size());
    assertEquals(110, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(125, facade.getShipPosition(ship1)[1], EPSILON);
    // Checking whether the information in the world's map has also been
    // changed.
    assertEquals(ship1, facade.getEntityAt(world, 110, 125));
    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(5, facade.getShipVelocity(ship1)[1], EPSILON);
    assertEquals(400, facade.getShipPosition(ship2)[0], EPSILON);
    assertEquals(115, facade.getShipPosition(ship2)[1], EPSILON);
    score += 8;
  }

  @Test
  public void testEvolveAfterShipShipCollision() throws ModelException {
    max_score += 15;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(500, 120, 10, 0, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(800, 120, -10, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 11, null);
    // collision after 10 seconds
    assertEquals(2, facade.getWorldShips(world).size());
    assertEquals(590, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(-10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    assertEquals(710, facade.getShipPosition(ship2)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship2)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship2)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship2)[1], EPSILON);
    score += 15;
  }

  @Test
  public void testEvolveAfterShipAsteroidCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(500, 120, 10, 0, 50, 0, 1000);
    Asteroid asteroid = facade.createAsteroid(800, 120, -10, 0, 50);
    facade.addShipToWorld(world, ship1);
    facade.addAsteroidToWorld(world, asteroid);
    facade.evolve(world, 11, null);
    // collision after 10 seconds
    assertEquals(1, facade.getWorldAsteroids(world).size());
    assertTrue(facade.getWorldShips(world).isEmpty());
    assertTrue(facade.isTerminatedShip(ship1));
    assertEquals(690, facade.getAsteroidPosition(asteroid)[0], EPSILON);
    assertEquals(120, facade.getAsteroidPosition(asteroid)[1], EPSILON);
    score += 12;
  }

  @Test
  public void testEvolveAfterAsteroidBulletCollision() throws ModelException {
    max_score += 5;
    World world = facade.createWorld(5000, 5000);
    Asteroid asteroid = facade.createAsteroid(1050, 120, -10, 0, 50);
    facade.addAsteroidToWorld(world, asteroid);
    Bullet bullet = facade.createBullet(80, 130, 10, 0, 20);
    facade.addBulletToWorld(world, bullet);
    // collision after 83 seconds
    facade.evolve(world, 84, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertEquals(0, facade.getWorldAsteroids(world).size());
    assertTrue(facade.isTerminatedBullet(bullet));
    assertTrue(facade.isTerminatedAsteroid(asteroid));
    score += 5;
  }

  @Test
  public void testEvolveAfterAsteroidAsteroidCollision() throws ModelException {
    max_score += 7;
    World world = facade.createWorld(5000, 5000);
    Asteroid asteroid1 = facade.createAsteroid(500, 120, 10, 0, 50);
    Asteroid asteroid2 = facade.createAsteroid(800, 120, -10, 0, 50);
    facade.addAsteroidToWorld(world, asteroid1);
    facade.addAsteroidToWorld(world, asteroid2);
    facade.evolve(world, 11, null);
    // collision after 10 seconds
    assertEquals(2, facade.getWorldAsteroids(world).size());
    assertEquals(590, facade.getAsteroidPosition(asteroid1)[0], EPSILON);
    assertEquals(120, facade.getAsteroidPosition(asteroid1)[1], EPSILON);
    assertEquals(-10, facade.getAsteroidVelocity(asteroid1)[0], EPSILON);
    assertEquals(0, facade.getAsteroidVelocity(asteroid1)[1], EPSILON);
    assertEquals(710, facade.getAsteroidPosition(asteroid2)[0], EPSILON);
    assertEquals(120, facade.getAsteroidPosition(asteroid2)[1], EPSILON);
    assertEquals(10, facade.getAsteroidVelocity(asteroid2)[0], EPSILON);
    assertEquals(0, facade.getAsteroidVelocity(asteroid2)[1], EPSILON);
    score += 7;
  }

  @Test
  public void testEvolveAfterShipPlanetoidCollision() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 12;
      World world = facade.createWorld(5000, 5000);
      Ship ship1 = facade.createShip(500, 120, 10, 0, 50, 0, 1000);
      Planetoid planetoid = facade.createPlanetoid(800, 120, -10, 0, 50, 0);
      facade.addShipToWorld(world, ship1);
      facade.addPlanetoidToWorld(world, planetoid);
      facade.evolve(world, 11, null);
      // collision after 10 seconds
      assertEquals(1, facade.getWorldPlanetoids(world).size());
      assertTrue(facade.getWorldShips(world).size() <= 1);
      assertTrue(facade.isTerminatedShip(ship1) || (facade.getShipWorld(ship1) == world));
      assertEquals(690, facade.getPlanetoidPosition(planetoid)[0], EPSILON);
      assertEquals(120, facade.getPlanetoidPosition(planetoid)[1], EPSILON);
      score += 12;
    }
  }

  @Test
  public void testEvolveShipBoundaryCollision() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, -10, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    // collision after 5 seconds
    facade.evolve(world, 15, null);
    assertEquals(1, facade.getWorldShips(world).size());
    assertEquals(150, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    score += 10;
  }

  @Test
  public void testEvolveShipDoubleBoundaryCollision() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 100, -10, -10, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    // collision after 15 seconds
    facade.evolve(world, 15, null);
    assertEquals(1, facade.getWorldShips(world).size());
    assertEquals(150, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(150, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship1)[1], EPSILON);
    score += 8;
  }

  @Test
  public void testEvolveBulletDiesOnThirdBounce() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(1000, 1000);
    Bullet bullet = facade.createBullet(200, 200, -10, 0, 50);
    facade.addBulletToWorld(world, bullet);
    // first bounce after 15 sec
    // second bounce after 105 sec
    // third bounce after 195 sec
    facade.evolve(world, 194.0, null);
    assertEquals(1, facade.getWorldBullets(world).size());
    assertEquals(60, facade.getBulletPosition(bullet)[0], EPSILON);
    assertEquals(200, facade.getBulletPosition(bullet)[1], EPSILON);
    assertEquals(-10, facade.getBulletVelocity(bullet)[0], EPSILON);
    assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
    facade.evolve(world, 2.0, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertTrue(facade.isTerminatedBullet(bullet));
    score += 12;
  }

  @Test
  public void testEvolveShipOwnBulletCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(1090, 120, 0, 0, 50, Math.PI, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet = facade.createBullet(1080, 130, -10, 0, 20);
    facade.loadBulletOnShip(ship, bullet);
    facade.fireBullet(ship);
    // collision with own ship after 8 seconds (a bit more for students working alone)
    facade.evolve(world, 9, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    if (nbStudentsInTeam > 1) {
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet));
      assertTrue(facade.getBulletShip(bullet) == ship);
      assertEquals(1090, facade.getBulletPosition(bullet)[0], EPSILON);
      assertEquals(120, facade.getBulletPosition(bullet)[1], EPSILON);
      assertTrue(facade.getBulletVelocity(bullet)[0] <= 250.0);
      assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
    }
    score += 12;
  }

  @Test
  public void testEvolveAfterOtherShipBulletCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(1050, 120, -10, 0, 50, Math.PI, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet = facade.createBullet(80, 130, 10, 0, 20);
    facade.addBulletToWorld(world, bullet);
    // collision after 83 seconds
    facade.evolve(world, 84, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertEquals(0, facade.getWorldShips(world).size());
    assertTrue(facade.isTerminatedBullet(bullet));
    assertTrue(facade.isTerminatedShip(ship));
    score += 12;
  }

  @Test
  public void testEvolveShipWithActiveThruster() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.5E20);
    facade.addShipToWorld(world, ship);
    facade.setThrusterActive(ship, true);
    assertEquals(1.0, facade.getShipAcceleration(ship), EPSILON);
    assertTrue(facade.isShipThrusterActive(ship));
    facade.evolve(world, 1, null);
    assertEquals(9, facade.getShipVelocity(ship)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
    score += 10;
  }

  @Test
  public void testTerminateShipInWorld() throws ModelException {
    max_score += 5;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
    facade.addShipToWorld(world, ship);
    facade.terminateShip(ship);
    assertTrue(facade.isTerminatedShip(ship));
    assertNull(facade.getShipWorld(ship));
    assertTrue(facade.getWorldShips(world).isEmpty());
    score += 5;
  }

  @Test
  public void testTerminateBulletLoadedOnShip() throws ModelException {
    max_score += 5;
    Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
    Bullet bullet = facade.createBullet(100, 120, -10, 0, 20);
    facade.loadBulletOnShip(ship, bullet);
    facade.terminateBullet(bullet);
    assertTrue(facade.isTerminatedBullet(bullet));
    assertNull(facade.getBulletShip(bullet));
    if (nbStudentsInTeam > 1)
      assertTrue(facade.getBulletsOnShip(ship).isEmpty());
    score += 5;
  }

  @Test
  public void testTerminateWorld() throws ModelException {
    max_score += 7;
    facade.terminateWorld(filledWorld);
    assertTrue(facade.isTerminatedWorld(filledWorld));
    assertEquals(0, facade.getEntities(filledWorld).size());
    assertNull(facade.getShipWorld(ship1));
    assertNull(facade.getBulletWorld(bullet1));
    score += 7;
  }

  // Assignment Statement

  @Test
  public void testAssignmentStatement_NewGlobalVariable() throws ModelException {
    max_score += 4;
    String code = "varname := 7.0;" + "print varname; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 7.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 4;
  }

  @Test
  public void testAssignmentStatement_LocalVariableSameNameGlobalVariable() throws ModelException {
    max_score += 12;
    String code = "def f { " + "  a := 10.0; " + "  return a; " + "} " + "a := 20.0; " + "print a; " + "print f(); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 20.0, 10.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 12;
  }

  @Test
  public void testAssignmentStatement_LocalVariableSameNameFunction() throws ModelException {
    max_score += 12;
    String code = "def g { " + "   return 1.0; " + "} " + "def f { " + "  g := 10.0; " + "  return g; " + "} "
        + "print f(); " + "print g(); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 10.0, 1.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 12;
  }

  @Test
  public void testAssignment_ImproperType() throws ModelException {
    try {
      max_score += 4;
      String code = "varname := 7.0; " + "varname := self; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testAssignment_NameAlreadyUsedForFunction() throws ModelException {
    try {
      max_score += 4;
      String code = "def f { " + "  return 1.0; " + "}" + "f := 10.0;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 4;
    }
  }

  // Print Statement

  @Test
  public void testPrintStatement_LegalCase() throws ModelException {
    max_score += 2;
    String code = "print 4.0;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 4.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 2;
  }

  @Test
  public void testPrintStatement_DirectlyInFunctionBody() throws ModelException {
    try {
      max_score += 5;
      String code = "def f { " + "  print 5.0; " + "}" + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testPrintStatement_IndirectlyInFunctionBody() throws ModelException {
    try {
      max_score += 5;
      String code = "def f { " + "  if self == self { " + "    print 5.0; " + "  }" + "}" + "print f();";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // Return Statement

  // Tests for correct return statements are part of the tests for
  // function calls.

  @Test
  public void testReturnStatement_NonInFunctionBody() throws ModelException {
    try {
      max_score += 5;
      String code = "return 4.0;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // If Statement

  @Test
  public void testIfStatement_ThenPartNonIterruptable() throws ModelException {
    max_score += 3;
    String code = "if self == self {" + "  print 4.0; " + "}";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 4.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testIfStatement_ThenPartIterruptable() throws ModelException {
    max_score += 12;
    String code = "print 2.0; " + "if self == self { " + "  print 4.0; " + "  skip; " + "  skip; " + "  print 8.0; "
        + "} " + "skip; " + "print 16.0;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    assertNull(facade.executeProgram(ship1, 0.25));
    score += 2;
    assertNull(facade.executeProgram(ship1, 0.25));
    assertNull(facade.executeProgram(ship1, 0.10));
    score += 2;
    List<Object> results = facade.executeProgram(ship1, 0.25);
    Object[] expecteds = { 2.0, 4.0, 8.0, 16.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 8;
  }

  @Test
  public void testIfStatement_ElsePartNonIterruptable() throws ModelException {
    max_score += 3;
    String code = "if self == 2.0 { " + "  print 4.0; " + "}" + "else { " + "print 8.0; " + "}";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 8.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testIfStatement_ElsePartIterruptable() throws ModelException {
    max_score += 12;
    String code = "print 2.0; " + "if self == 22.22  " + "  { print 33.33; } " + "else "
        + "  { print 4.0; skip; skip; print 8.0; } " + "skip; " + "print 16.0; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    assertNull(facade.executeProgram(ship1, 0.25));
    score += 2;
    assertNull(facade.executeProgram(ship1, 0.25));
    assertNull(facade.executeProgram(ship1, 0.10));
    score += 2;
    List<Object> results = facade.executeProgram(ship1, 0.25);
    assertEquals(4, results.size());
    score += 2;
    Object[] expecteds = { 2.0, 4.0, 8.0, 16.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 6;
  }

  @Test
  public void testIfStatement_NoElsePart() throws ModelException {
    max_score += 3;
    String code = "if self == 4.0 " + "  { print 4.0; } ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    assertEquals(0, results.size());
    score += 3;
  }

  @Test
  public void testIfStatement_NonBooleanControllingExpression() throws ModelException {
    try {
      max_score += 5;
      String code = "if self { " + "  print 4.0; " + "}";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // Sequence Statement

  @Test
  public void testSequenceStatement_NonNestedNonIterruptable() throws ModelException {
    max_score += 3;
    String code = "print 4.0; " + "print 12.0; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 4.0, 12.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testSequenceStatement_NonNestedIterruptable() throws ModelException {
    max_score += 10;
    String code = "print 4.0; " + "skip; " + "skip; " + "print 3.0; " + "print 7.0; " + "skip; " + "print 5.0; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.05);
    assertNull(results);
    results = facade.executeProgram(ship1, 0.25);
    assertNull(results);
    score += 2;
    results = facade.executeProgram(ship1, 0.25);
    assertNull(results);
    results = facade.executeProgram(ship1, 0.03);
    assertNull(results);
    results = facade.executeProgram(ship1, 0.23);
    Object[] expecteds = { 4.0, 3.0, 7.0, 5.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 8;
  }

  // Fire statement

  @Test
  public void testFireStatement_EnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "fire; " + "print 0.4; ";
    int oldNbBullets = facade.getNbBulletsOnShip(ship1);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.45);
    assertEquals(oldNbBullets - 1, facade.getNbBulletsOnShip(ship1));
    Object[] expecteds = { 0.4 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testFireStatement_NotEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "fire; " + "print 0.4; ";
    int oldNbBullets = facade.getNbBulletsOnShip(ship1);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.15);
    assertEquals(oldNbBullets, facade.getNbBulletsOnShip(ship1));
    assertNull(results);
    score += 3;
  }

  @Test
  public void testFireStatement_InFunctionBody() throws ModelException {
    try {
      max_score += 3;
      String code = "def f { " + "  fire; " + "  return 5.0; " + "}" + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // Turn statement

  @Test
  public void testTurnStatement_ValidAngleEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "turn 1.0; " + "print 0.4; ";
    facade.turn(ship1, 1.5);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.45);
    assertEquals(2.5, facade.getShipOrientation(ship1), EPSILON);
    Object[] expecteds = { 0.4 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testTurnStatement_NotEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "turn 1.0; " + "print 0.4; ";
    facade.turn(ship1, 1.5);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.15);
    assertEquals(1.5, facade.getShipOrientation(ship1), EPSILON);
    assertNull(results);
    score += 3;
  }

  @Test
  public void testTurnStatement_InvalidAngle() throws ModelException {
    max_score += 5;
    try {
      String code = "turn 10.0; " + "print 0.4; ";
      facade.turn(ship1, 1.5);
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 0.45);
      // It is allowed to do nothing in case of an illegal angle.
      assertEquals(1.5, facade.getShipOrientation(ship1), EPSILON);
      Object[] expecteds = { 0.4 };
      assertArrayEquals(expecteds, results.toArray());
      score += 5;
    } catch (ModelException exc) {
      assertEquals(1.5, facade.getShipOrientation(ship1), EPSILON);
      score += 5;
    }
  }

  @Test
  public void testTurnStatement_InFunctionBody() throws ModelException {
    try {
      max_score += 3;
      String code = "def f { " + "  turn 1.0; " + "  return 5.0; " + "}" + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // Thruster ON statement

  @Test
  public void testThrusterOnStatement_EnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 0.45);
      assertTrue(facade.isShipThrusterActive(ship1));
      Object[] expecteds = { 0.4 };
      assertArrayEquals(expecteds, results.toArray());
      score += 3;
    }
  }

  @Test
  public void testThrusterStatement_NotEnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 0.15);
      assertFalse(facade.isShipThrusterActive(ship1));
      assertNull(results);
      score += 3;
    }
  }

  @Test
  public void testThrusterOnStatement_InFunctionBody() throws ModelException {
    if (nbStudentsInTeam > 1) {
      try {
        max_score += 3;
        String code = "def f { " + "  thrust; " + "  return 5.0; " + "}" + "print f(); ";
        Program program = ProgramParser.parseProgramFromString(code, programFactory);
        facade.loadProgramOnShip(ship1, program);
        facade.executeProgram(ship1, 0.3);
        fail();
      } catch (ModelException exc) {
        score += 3;
      }
    }
  }

  // Thruster OFF statement

  @Test
  public void testThrusterOffStatement_EnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; " + "thrust_off; " + "print 0.8; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      assertTrue(facade.isShipThrusterActive(ship1));
      List<Object> results = facade.executeProgram(ship1, 0.35);
      assertFalse(facade.isShipThrusterActive(ship1));
      Object[] expecteds = { 0.4, 0.8 };
      assertArrayEquals(expecteds, results.toArray());
      score += 3;
    }
  }

  @Test
  public void testThrusterOffStatement_NotEnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; " + "thrust_off; " + "print 0.8;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.2);
      assertTrue(facade.isShipThrusterActive(ship1));
      List<Object> results = facade.executeProgram(ship1, 0.15);
      assertTrue(facade.isShipThrusterActive(ship1));
      assertNull(results);
      score += 3;
    }
  }

  @Test
  public void testThrusterOffStatement_InFunctionBody() throws ModelException {
    if (nbStudentsInTeam > 1) {
      try {
        max_score += 3;
        String code = "def f { " + "  thrust_off; " + "  return 5.0; " + "}" + "print f(); ";
        Program program = ProgramParser.parseProgramFromString(code, programFactory);
        facade.loadProgramOnShip(ship1, program);
        facade.executeProgram(ship1, 0.3);
        fail();
      } catch (ModelException exc) {
        score += 3;
      }
    }
  }

  // Skip statement

  @Test
  public void testSkipStatement_EnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "skip; " + "print 0.4;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 0.4 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testSkipStatement_NotEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "skip; " + "print 0.4;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.1);
    assertNull(results);
    score += 3;
  }

  @Test
  public void testSkipStatement_InFunctionBody() throws ModelException {
    try {
      max_score += 3;
      String code = "def f { " + "  skip; " + "  return 5.0; " + "}" + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // While Statement

  @Test
  public void testWhileStatement_ZeroIterations() throws ModelException {
    max_score += 5;
    String code = "while 3.0 < 1.0 { " + "  print 4.0; " + "}";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    assertEquals(0, results.size());
    score += 5;
  }

  @Test
  public void testWhileStatement_SeveralIterations() throws ModelException {
    max_score += 18;
    String code = "a := 10; " + "while a < 20.5 { " + "  print a; " + "  a := a + 2.0; " + "}";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 10.0, 12.0, 14.0, 16.0, 18.0, 20.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 18;
  }

  @Test
  public void testWhileStatement_Interruptable() throws ModelException {
    max_score += 25;
    String code = "a := 10; " + "while a < 20.5 { " + "  print a; " + "  skip; " + "  a := a + 2.0; " + "}"
        + "print 0.0; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.3);
    assertNull(results);
    score += 5;
    results = facade.executeProgram(ship1, 0.65);
    assertNull(results);
    score += 5;
    results = facade.executeProgram(ship1, 0.57);
    Object[] expecteds = { 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 0.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 15;
  }

  @Test
  public void testWhileStatement_NestedWhiles() throws ModelException {
    max_score += 20;
    String code = "a := 10; " + "sum := 0.0; " + "while 0.5 < a { " + "  temp := 6.0;" + "  while 0.5 < temp { "
        + "    sum := sum + (temp*a); " + "    temp := temp + -1.0;" + "  } " + "  a := a + -1.0; " + "}"
        + "print sum; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.3);
    Object[] expecteds = { 1155.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 20;
  }

  @Test
  public void testWhileStatement_InsideRecursiveFunction() throws ModelException {
    max_score += 20;
    String code = "def sumfac { " + "  a := $1; " + "  t := 1.0; " + "  while 1.5 < a { "
        + "    t := t + (a*sumfac(a + -1.0));" + "    a := a + -1.0; " + "  }" + "  return t; " + "} "
        + "print sumfac(4.0); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.3);
    Object[] expecteds = { 60.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 20;
  }

  @Test
  public void testWhileStatement_NonBooleanControllingExpression() throws ModelException {
    try {
      max_score += 5;
      String code = "while self { " + "  print 4.0; " + "}";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // Break Statement

  @Test
  public void testBreakStatement_NonNestedCase() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 16;
      String code = "a := 10; " + "while a < 20.5 { " + "  print a; " + "  if 14.5 < a { " + "    break; " + "  }"
          + "  a := a + 2.0; " + "}" + "print 0.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { 10.0, 12.0, 14.0, 16.0, 0.0 };
      assertArrayEquals(expecteds, results.toArray());
      score += 16;
    }
  }

  @Test
  public void testBreakStatement_NestedCase() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 21;
      String code = "a := 10; " + "while a < 20.5 { " + "  print a; " + "  while a < 15.0 { " + "    a := a + 1.0;"
          + "    if 12.5 < a { " + "      break; " + "    }" + "  }" + "  a := a + 2.0; " + "}" + "print 0.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { 10.0, 15.0, 17.0, 19.0, 0.0 };
      assertArrayEquals(expecteds, results.toArray());
      score += 21;
    }
  }

  @Test
  public void testBreak_OutsideWhile() throws ModelException {
    try {
      max_score += 9;
      String code = "break; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 9;
    }
  }

  @Test
  public void testBreakStatement_InFunctionBody() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 16;
      String code = "def f { " + "  break; " + "  return 0.0;" + "}" + "a := 10; " + "while a < 20.5 { " + "  print a; "
          + "  if 14.5 < a { " + "    b := f(); " + "  }" + "  a := a + 2.0; " + "}" + "print 0.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { 10.0, 12.0, 14.0, 16.0, 0.0 };
      assertArrayEquals(expecteds, results.toArray());
      score += 16;
    }
  }

  // Read Variable

  @Test
  public void testReadVariable_GlobalVariable() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 10;
      String code = "def f { " + "  return a; " + "}" + "a := 10; " + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { 10.0 };
      assertArrayEquals(expecteds, results.toArray());
      score += 10;
    }
  }

  @Test
  public void testReadVariable_UndefinedVariable() throws ModelException {
    try {
      max_score += 5;
      String code = "def f { " + "  return 2.0; " + "}" + "print f; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testReadVariable_VariableDefinedAsFunction() throws ModelException {
    try {
      max_score += 3;
      String code = "print xxx;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testReadVariable_DefinedInInvoingFunction() throws ModelException {
    try {
      max_score += 12;
      String code = "def g { " + "   return x; " + "} " + "def f { " + "  x := 10; " + "  return g(); " + "} "
          + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 12;
    }
  }

  // Read Parameter

  @Test
  public void testReadParameter_LegalCase() throws ModelException {
    max_score += 8;
    String code = "def f { " + "  return $1; " + "}" + "print f(22.0); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 22.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 8;
  }

  @Test
  public void testReadParameter_OutsideFunctionBody() throws ModelException {
    try {
      max_score += 3;
      String code = "print $1;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // Null

  @Test
  public void testNull() throws ModelException {
    max_score += 3;
    String code = "print null; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { null };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  // Self

  @Test
  public void testSelf() throws ModelException {
    max_score += 3;
    String code = "print self; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { ship1 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  // Ship

  @Test
  public void testShip_OtherShipsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 14;
      String code = "print ship; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      Ship ship2 = facade.createShip(200, 200, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship2);
      Ship ship3 = facade.createShip(250, 250, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship3);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { ship2 };
      assertArrayEquals(expecteds, results.toArray());
      score += 14;
    }
  }

  @Test
  public void testShip_NoOtherShipsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 8;
      String code = "print ship; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { null };
      assertArrayEquals(expecteds, results.toArray());
      score += 8;
    }
  }

  // Asteroid

  @Test
  public void testAsteroid_AsteroidsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 4;
      String code = "print asteroid; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      Asteroid asteroid1 = facade.createAsteroid(200, 200, 0, 0, 20);
      facade.addAsteroidToWorld(world, asteroid1);
      Asteroid asteroid2 = facade.createAsteroid(250, 250, 0, 0, 20);
      facade.addAsteroidToWorld(world, asteroid2);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { asteroid1 };
      assertArrayEquals(expecteds, results.toArray());
      score += 4;
    }
  }

  @Test
  public void testAsteroid_NoAsteroidsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 2;
      String code = "print asteroid; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { null };
      assertArrayEquals(expecteds, results.toArray());
      score += 2;
    }
  }

  // Planetoid

  @Test
  public void testPlanetoid_PlanetoidsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 4;
      String code = "print planetoid; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      Planetoid planetoid1 = facade.createPlanetoid(200, 200, 0, 0, 20, 0);
      facade.addPlanetoidToWorld(world, planetoid1);
      Planetoid planetoid2 = facade.createPlanetoid(250, 250, 0, 0, 20, 0);
      facade.addPlanetoidToWorld(world, planetoid2);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { planetoid1 };
      assertArrayEquals(expecteds, results.toArray());
      score += 4;
    }
  }

  @Test
  public void testAsteroid_NoPlanetoidsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 2;
      String code = "print planetoid; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { null };
      assertArrayEquals(expecteds, results.toArray());
      score += 2;
    }
  }

  // Bullet

  @Test
  public void testBullet_FiredBulletsInWorld() throws ModelException {
    max_score += 12;
    String code = "print bullet; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    Set<? extends Bullet> bulletsOnShip1 = null;
    if (nbStudentsInTeam > 1)
      bulletsOnShip1 = facade.getBulletsOnShip(ship1);
    facade.fireBullet(ship1);
    facade.fireBullet(ship1);
    facade.fireBullet(ship1);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    assertEquals(1, results.size());
    assertTrue(facade.getWorldBullets(filledWorld).contains(results.get(0)));
    if (nbStudentsInTeam > 1)
      assertTrue(bulletsOnShip1.contains(results.get(0)));
    score += 12;
  }

  @Test
  public void testBullet_NoFiredBulletsInWorld() throws ModelException {
    max_score += 7;
    String code = "print bullet; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { null };
    assertArrayEquals(expecteds, results.toArray());
    score += 7;
  }

  // Planet

  @Test
  public void testPlanet_PlanetsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 4;
      String code = "print planet; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      Asteroid asteroid1 = facade.createAsteroid(200, 200, 0, 0, 20);
      facade.addAsteroidToWorld(world, asteroid1);
      Planetoid planetoid1 = facade.createPlanetoid(250, 250, 0, 0, 20, 0);
      facade.addPlanetoidToWorld(world, planetoid1);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { asteroid1 };
      assertArrayEquals(expecteds, results.toArray());
      score += 4;
    }
  }

  @Test
  public void testPlanet_NoPlanetsInWorld() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 2;
      String code = "print planet; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      World world = facade.createWorld(2000, 2000);
      Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { null };
      assertArrayEquals(expecteds, results.toArray());
      score += 2;
    }
  }

  // Any

  @Test
  public void testAny_SeveralEntitiesInWorld() throws ModelException {
    max_score += 10;
    String code = "print any; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    World world = facade.createWorld(2000, 2000);
    Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
    for (int i = 1; i < 10; i++) {
      Bullet bulletToLoad = facade.createBullet(100, 100, 0, 0, 10);
      facade.loadBulletOnShip(ship1, bulletToLoad);
    }
    facade.fireBullet(ship1);
    facade.fireBullet(ship1);
    facade.addShipToWorld(world, ship1);
    Asteroid asteroid1 = facade.createAsteroid(200, 200, 0, 0, 20);
    facade.addAsteroidToWorld(world, asteroid1);
    Planetoid planetoid1 = facade.createPlanetoid(250, 250, 0, 0, 20, 0);
    facade.addPlanetoidToWorld(world, planetoid1);
    Set<? extends Object> allEntities = facade.getEntities(world);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    assertEquals(1, results.size());
    assertTrue(allEntities.contains(results.get(0)));
    score += 10;
  }

  @Test
  public void testPlanet_NoOtherEntitiesInWorld() throws ModelException {
    max_score += 6;
    String code = "print any; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    World world = facade.createWorld(2000, 2000);
    Ship ship1 = facade.createShip(100, 100, 0, 0, 20, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { ship1 };
    assertArrayEquals(expecteds, results.toArray());
    score += 6;
  }

  // Change Sign

  @Test
  public void testChangeSign_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print - 4.0 ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { -4.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testChangeSign_IllegalCase() throws ModelException {
    try {
      max_score += 5;
      String code = "print - self; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // Addition

  @Test
  public void testAddition_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print 4.0 + 5.0; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 9.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testAddition_IllegalCase() throws ModelException {
    try {
      max_score += 5;
      String code = "print 4.0 + self; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // Multiplication

  @Test
  public void testMultiplication_LegalCase() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "print 4.0 * 5.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 1.0);
      Object[] expecteds = { 20.0 };
      assertArrayEquals(expecteds, results.toArray());
      score += 3;
    }
  }

  @Test
  public void testMultiplication_IllegalCase() throws ModelException {
    if (nbStudentsInTeam > 1) {
      try {
        max_score += 5;
        String code = "print 4.0 * self; ";
        Program program = ProgramParser.parseProgramFromString(code, programFactory);
        facade.loadProgramOnShip(ship1, program);
        facade.executeProgram(ship1, 1.0);
        fail();
      } catch (ModelException exc) {
        score += 5;
      }
    }
  }

  // Function Call

  @Test
  public void testFunctionCall_NoParameters() throws ModelException {
    max_score += 10;
    String code = "def f { " + "  return 5.0; " + "}" + "print f(); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.3);
    Object[] expecteds = { 5.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 10;
  }

  @Test
  public void testFunctionCall_WithParameters() throws ModelException {
    max_score += 10;
    String code = "def f { " + "  return $1 + $2; " + "}" + "print f(3.0,7.0); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.3);
    Object[] expecteds = { 10.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 10;
  }

  @Test
  public void testFunctionCall_LocalVariable() throws ModelException {
    max_score += 10;
    String code = "def f { " + "  a := 10; " + "  return a; " + "}" + "print f(); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 10.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 10;
  }

  @Test
  public void testFunctionCall_AccessLocalVariableOutsideBody() throws ModelException {
    try {
      max_score += 9;
      String code = "def f { " + "  x := 10; " + "  return x; " + "} " + "print f()+x; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 9;
    }
  }

  @Test
  public void testFunctionCall_RecursiveFunction() throws ModelException {
    max_score += 20;
    String code = "def fac { " + "  if $1 < 1.5 { " + "    return 1.0; " + "  }" + "  else { "
        + "    return $1 * fac($1+-1.0); " + "  }" + "}" + "print fac(4.0); ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.3);
    Object[] expecteds = { 1.0 * 2.0 * 3.0 * 4.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 20;
  }

  @Test
  public void testFunctionCall_UndefinedFunction() throws ModelException {
    try {
      max_score += 4;
      String code = "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testFunctionCall_GlobalVariableWithFunctionName() throws ModelException {
    try {
      max_score += 4;
      String code = "f := 4.0; " + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testFunctionCall_IllegalActualArgument() throws ModelException {
    try {
      max_score += 5;
      String code = "def f { " + "  return $1; " + "}" + "print f(self + 3.0); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testFunctionCall_NotEnoughActualArguments() throws ModelException {
    try {
      max_score += 6;
      String code = "def f { " + "  return $1 + $2; " + "}" + "print f(3.0); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 6;
    }
  }

  // Not

  @Test
  public void testNot_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print ! (4.0 == 6.0) ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { true };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testNot_IllegalCase() throws ModelException {
    try {
      max_score += 5;
      String code = "print ! self; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // Square Root

  @Test
  public void testSqrt_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print sqrt 4.0 ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 2.0 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testSqrt_IllegalCase() throws ModelException {
    try {
      max_score += 5;
      String code = "print sqrt self; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

  // GetX

  @Test
  public void testGetX_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print getx self ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { facade.getShipPosition(ship1)[0] };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testGetX_IllegalCase() throws ModelException {
    try {
      max_score += 2;
      String code = "print getx 4.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testGetX_NullEntity() throws ModelException {
    try {
      max_score += 3;
      String code = "print getx null; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // GetY

  @Test
  public void testGetY_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print gety self ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { facade.getShipPosition(ship1)[1] };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testGetY_IllegalCase() throws ModelException {
    try {
      max_score += 2;
      String code = "print gety 4.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testGetY_NullEntity() throws ModelException {
    try {
      max_score += 3;
      String code = "print gety null; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // GetVX

  @Test
  public void testGetVX_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print getvx self ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { facade.getShipVelocity(ship1)[0] };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testGetVX_IllegalCase() throws ModelException {
    try {
      max_score += 2;
      String code = "print getvx 4.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testGetVX_NullEntity() throws ModelException {
    try {
      max_score += 3;
      String code = "print getvx null; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // GetVY

  @Test
  public void testGetVY_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print getvy self ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { facade.getShipVelocity(ship1)[1] };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testGetVY_IllegalCase() throws ModelException {
    try {
      max_score += 2;
      String code = "print getvy 4.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testGetVY_NullEntity() throws ModelException {
    try {
      max_score += 3;
      String code = "print getvy null; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // GetRadius

  @Test
  public void testGetRadius_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print getradius self ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { facade.getShipRadius(ship1) };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testGetRadius_IllegalCase() throws ModelException {
    try {
      max_score += 2;
      String code = "print getradius 4.0; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testGetRadius_NullEntity() throws ModelException {
    try {
      max_score += 3;
      String code = "print getradius null; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // GetDirection

  @Test
  public void testGetDirection_LegalCase() throws ModelException {
    max_score += 3;
    String code = "print getdir ; ";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.turn(ship1, 0.33);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { facade.getShipOrientation(ship1) };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  // Equality

  @Test
  public void testEquality_TrueCase() throws ModelException {
    max_score += 3;
    String code = "print self == self;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { true };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testEquality_FalseCase() throws ModelException {
    max_score += 3;
    String code = "print self == 4.0;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { false };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  // Less Than

  @Test
  public void testLessThan_TrueCase() throws ModelException {
    max_score += 3;
    String code = "print 4.0 < 6.0;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { true };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testLessThan_FalseCase() throws ModelException {
    max_score += 3;
    String code = "print 6.0 < 4.0;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { false };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testLessThan_IllegalCase() throws ModelException {
    try {
      max_score += 5;
      String code = "print 4.0 < self;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 1.0);
      fail();
    } catch (ModelException exc) {
      score += 5;
    }
  }

}