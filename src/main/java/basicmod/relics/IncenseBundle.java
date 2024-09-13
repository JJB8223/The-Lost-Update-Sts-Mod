package basicmod.relics;

import static basicmod.BasicMod.makeID;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class IncenseBundle extends BaseRelic{
    private static final String NAME = "incenseBundle"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked

    public IncenseBundle() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onExhaust(AbstractCard card) {
        if(!card.isEthereal){
        
            ArrayList<AbstractPower> debuffs = new ArrayList<>();
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    debuffs.add(power);
                }
            }

            if (!debuffs.isEmpty()) {
                AbstractPower randomDebuff = debuffs.get(AbstractDungeon.relicRng.random(debuffs.size() - 1));
                if (randomDebuff.amount > 1) {
                    randomDebuff.amount--;
                    randomDebuff.updateDescription();
                } else {
                    addToTop((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, randomDebuff));
                }
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new IncenseBundle();
    }
} 