package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class KingWorm extends BaseRelic{
    private static final String NAME = "kingWorm"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    private float MODIFIER_AMT = 0.15F;

    public KingWorm() {
        super(ID, NAME, RARITY, SOUND);
    }


   public void atBattleStart() {
      for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
        if (AbstractDungeon.miscRng.randomBoolean(0.02f)) {
            if (m != null) {
                m.setMove((byte) 4, AbstractMonster.Intent.STRONG_DEBUFF);
                m.createIntent();
            }
        }
        if (m.currentHealth > (int)(m.maxHealth * (1.0F - this.MODIFIER_AMT))) {
          m.currentHealth = (int)(m.maxHealth * (1.0F - this.MODIFIER_AMT));
          m.healthBarUpdatedEvent();
        } 
      } 
      addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
    }

    public void onMonsterDeath(AbstractMonster m) {
    if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
      flash();
      addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)m, this));
      addToBot((AbstractGameAction)new AddCardToDeckAction((AbstractCard) new Parasite()));
    } 
  }
} 