package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class RustyBreastplate extends BaseRelic{
    private static final String NAME = "rustyBreastplate"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked

    public RustyBreastplate() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onTurnStart(){
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if(m.getIntentDmg() != 0){
                m.setIntentBaseDmg(m.getIntentDmg()/2);
            }
        }
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount){
        flash(); 
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PoisonPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
        return damageAmount / 2;
    }

    public AbstractRelic makeCopy() {
        return new RustyBreastplate();
    }
} 