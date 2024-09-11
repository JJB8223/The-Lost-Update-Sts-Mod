package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class BrokenSyringe extends BaseRelic{
    private static final String NAME = "brokenSyringe"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked
    private boolean firstAttack;

    public BrokenSyringe() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
        this.firstAttack = true;
    }



    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
      if(firstAttack){
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 8, 0.0F));
        firstAttack = false;
      }
      return damageAmount;
    }

    public AbstractRelic makeCopy() {
    return new BrokenSyringe();
    }
} 