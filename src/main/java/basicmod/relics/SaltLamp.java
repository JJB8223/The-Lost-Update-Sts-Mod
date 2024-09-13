package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class SaltLamp extends BaseRelic{
    private static final String NAME = "saltLamp"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked
    private static final int EXTRA_HEAL = 6;

    public SaltLamp() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onHeal(int healAmount) {
        if (AbstractDungeon.player.hasRelic(this.ID)) {
            flash(); 
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this)); 
            addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, EXTRA_HEAL)); 
        }
    }

    public AbstractRelic makeCopy() {
        return new SaltLamp();
    }
} 