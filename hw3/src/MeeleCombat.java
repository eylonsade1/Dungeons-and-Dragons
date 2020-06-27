import BusinessLayer.Tiles.Unit;

public class MeeleCombat {

    public String meeleCombat (Unit attacker, Unit defender) {
        String combatSummary = attacker.getName() + " engaged in combat with " + defender + ".\n" + attacker.describe() + "\n" + defender.describe() + "\n";
        combatSummary += attacker.engageCombat(defender);
        return null;
    }






}
