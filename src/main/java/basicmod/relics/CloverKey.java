package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.MeatOnTheBone;


public class CloverKey extends BaseRelic{
    private static final String NAME = "cloverKey"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked
    private boolean nextChest;

    public CloverKey() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
        this.nextChest = true;
    }



    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onChestOpen(boolean bossChest) {
        if (nextChest) {
            flash();
            addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
            if (AbstractDungeon.relicRng.randomBoolean(0.75F)) {
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
            } else {
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
            } 
            this.description = this.DESCRIPTIONS[1];
            nextChest = false;
        } 
    }

    public AbstractRelic makeCopy() {
        return new CloverKey();
    }
} 