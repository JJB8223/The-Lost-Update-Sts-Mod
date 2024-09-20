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
    private static final float PARASITE_CHANCE = 0.02F;

    public ConquerorWorm() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
      return this.DESCRIPTIONS[0];
    }

    public void atBattleStart(){
      for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
        if (m != null && !m.isDeadOrEscaped()) {
            // Reduce current health by 15% of max health
            int healthReduction = (int) (m.maxHealth * MODIFIER_AMT);
            m.currentHealth -= healthReduction;
            m.healthBarUpdatedEvent(); // Update health bar visually
        }
      } 
    }

   public void atTurnStart() {
    for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
      if (m != null && !m.isDeadOrEscaped()) {
          // 2% chance to give the player a Parasite
          if (AbstractDungeon.miscRng.randomBoolean(PARASITE_CHANCE)) {
              flash(); // Visual indication that the relic activates

              // Add a "Parasite" card to the player's deck
              AbstractDungeon.actionManager.addToBottom(
                      new AddCardToDeckAction(CardLibrary.getCard(Parasite.ID).makeCopy())
              );

              // Add a shake effect to the monster
              AbstractDungeon.actionManager.addToBottom(new FastShakeAction(m, 0.5F, 0.2F));
              
              // Show relic effect above the creature
              AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));
          }
      }
    }
  }

    public AbstractRelic makeCopy() {
      return new ConquerorWorm();
    }

} 