package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class AshTray extends BaseRelic{
    private static final String NAME = "ashTray"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked

    public AshTray() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
    }

    public static final int COUNT = 3;

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onExhaust(AbstractCard card){
        this.counter++;
    
        if (this.counter == 3) {
            this.counter = 0;
            flash();
            this.pulse = false;
        } else if (this.counter == 2) {
            beginPulse();
            this.pulse = true;
            AbstractDungeon.player.hand.refreshHandLayout();
            addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
            addToBot((AbstractGameAction)new GainEnergyAction(card.energyOnUse));
        } 
    }

} 