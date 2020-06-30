package BusinessLayer.Tiles.UnitTesting;

import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Enemies.Monster;
import BusinessLayer.Tiles.Units.Health;
import BusinessLayer.Tiles.Units.Players.Rogue;
import BusinessLayer.Tiles.Units.Players.Warrior;
import BusinessLayer.Tiles.Wall;
import org.junit.*;

public class UnitTest {
    private Unit unit;
    private Unit otherUnit;
    private Wall wall;

    @Before
    public void unitTest () {
        unit = new Warrior(new Position(0, 0), "Knight", new Health(150), 30, 2, 2);
        otherUnit = new Monster('^',new Position(0,2),"Dragon",new Health(30),20,6,3,2);
    }
    @Test
    public void testContactWith(){//tests if a unit contacts a unit it brings to a combat
        Assert.assertTrue("Player contacting with Monster should bring to a combat", unit.contactWith(otherUnit).indexOf("Knight engaged in combat with Dragon")!=-1);
    }
    @Before
    public void unitTest2 () {//tests if a unit contacts a wall nothing happens
        unit = new Warrior(new Position(0, 0), "Knight", new Health(150), 30, 2, 2);
        wall = new Wall(new Position(0,1));
    }
    @Test
    public void testContactWith2 () {
         Assert.assertTrue("Player contacting a #wall# nothing should happan", unit.contactWith(wall).isEmpty());
    }
    @Before
    public void unitTest3 () {
        unit = new Warrior(new Position(0, 0), "Knight", new Health(150), 30, 2, 2);
        otherUnit = new Monster('^',new Position(0,2),"Dragon",new Health(30),20,6,3,2);
    }
    @Test
    public void testKill () {
        unit.kill(otherUnit);
        Assert.assertEquals("Player should be dead",unit.getTileType(),'X');
    }

}