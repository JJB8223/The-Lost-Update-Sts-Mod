package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class CollectorsSleeves extends BaseRelic{
    private static final String NAME = "collectorsSleves"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked

    public CollectorsSleeves() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }
        
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }
    
    public void onPlayerEntry(){
        AbstractDungeon.getRewardCards();
    }

    public AbstractRelic makeCopy() {
        return new CollectorsSleeves();
    }
} 
