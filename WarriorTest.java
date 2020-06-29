package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import org.junit.*;


class WarriorTest {

    Warrior w;
    @Before
    public void UnitTest () {
        w= new Warrior(new Position(0,0),"Dragon Warrior", new Health(300), 50,5,3);
    }

    @Test
    void castAbility() {
        UnitTest();
        w.setRemainingCoolDown(3);
        Assert.assertEquals("Warrior had to cool-Down","Can not cast Avenger's Shield, 2/2 game ticks left until cooldown",w.castAbility());


    }
}