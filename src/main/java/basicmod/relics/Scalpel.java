package basicmod.relics;

import static basicmod.BasicMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class Scalpel extends BaseRelic{
    private static final String NAME = "scalpel"; 
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked

    public Scalpel() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        if (!AbstractDungeon.player.masterDeck.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck,1,"Select a card to remove",false);
        }
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard cardToRemove = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.player.masterDeck.removeCard(cardToRemove);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    public AbstractRelic makeCopy() {
        return new Scalpel();
    }
} 