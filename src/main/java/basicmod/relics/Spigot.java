package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class Spigot extends BaseRelic{
    private static final String NAME = "spigot"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked

    public Spigot() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        if(AbstractDungeon.player.powers.size() >= 2){
            addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
            addToTop((AbstractGameAction)new DrawCardAction(1));
        }
    }

    public AbstractRelic makeCopy() {
    return new Spigot();
    }
} 