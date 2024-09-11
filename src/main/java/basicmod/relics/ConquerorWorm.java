package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.lang.reflect.Field;


public class ConquerorWorm extends BaseRelic{
    private static final String NAME = "conquerorWorm"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    private float MODIFIER_AMT = 0.15F;
    private AbstractMonster.Intent moveIntent;
    private byte moveByte;
    private EnemyMoveInfo move;

    public ConquerorWorm() {
        super(ID, NAME, RARITY, SOUND);
    }


    public void atBattleStart(){
      for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
        m.currentHealth = m.currentHealth - (int)(m.maxHealth * (this.MODIFIER_AMT));
        m.healthBarUpdatedEvent();
      } 
    }

   public void atTurnStart() {
      for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
        if (AbstractDungeon.miscRng.randomBoolean(0.50f)) {
          if (m != null) {
            moveByte =  ((AbstractMonster) m).nextMove;
            moveIntent = ((AbstractMonster) m).intent;

            try {
              Field f = AbstractMonster.class.getDeclaredField("move");
              f.setAccessible(true);
              move = (EnemyMoveInfo) f.get(m);
              EnemyMoveInfo stunMove = new EnemyMoveInfo(moveByte, AbstractMonster.Intent.STRONG_DEBUFF, -1, 0, false);
              f.set(m, stunMove);
              ((AbstractMonster) m).createIntent();
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }

            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)m, 0.5F, 0.2F));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AddCardToDeckAction(CardLibrary.getCard("Parasite").makeCopy()));
          }
        }
      } 
    }

    public AbstractRelic makeCopy() {
    return new ConquerorWorm();
    }

} 