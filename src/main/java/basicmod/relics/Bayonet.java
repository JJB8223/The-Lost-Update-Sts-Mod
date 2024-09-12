package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Bayonet extends BaseRelic {
    private static final String NAME = "bayonet"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked
    private boolean firstCardPlayed = false;

    public Bayonet() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart(){
        firstCardPlayed = true;
    }

    public void onPlayCard(AbstractCard card, AbstractCreature target){
        if(firstCardPlayed && card.costForTurn >= 2){
            if(AbstractDungeon.relicRng.randomBoolean(0.02f)){
                flash();
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(card.costForTurn));
            }
        }
    }

    public AbstractRelic makeCopy() {
      return new Bayonet();
    }
}
