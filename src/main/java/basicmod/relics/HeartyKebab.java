package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class HeartyKebab extends BaseRelic {
    private static final String NAME = "heartyKebab"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked

    private int damageTakenLimit;

    public HeartyKebab() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
    }

    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0];
    }

    public void resetDamageLimit(){
        int newDmgLimit = AbstractDungeon.player.maxHealth/2;
        this.damageTakenLimit = newDmgLimit;
        counter = newDmgLimit;
    }

    public void atBattleStart(){    
        resetDamageLimit();
    }
    
    public void atTurnStart(){
        resetDamageLimit();
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount){
        if(info.owner == null || damageAmount <= 0){
            return damageAmount; //ignoring non damaging attacks
        }

        if(damageAmount > damageTakenLimit ){
            damageAmount = damageTakenLimit;
        }

        damageTakenLimit =- damageAmount;

        return damageAmount;
    }

    public AbstractRelic makeCopy(){
        return new HeartyKebab();
    }
}
