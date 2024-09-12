// package basicmod.relics;

// import static basicmod.BasicMod.makeID;

// import com.megacrit.cardcrawl.actions.AbstractGameAction;
// import com.megacrit.cardcrawl.actions.common.DrawCardAction;
// import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
// import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
// import com.megacrit.cardcrawl.core.AbstractCreature;
// import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
// import com.megacrit.cardcrawl.powers.AbstractPower;
// import com.megacrit.cardcrawl.relics.AbstractRelic;


// public class IncenseBundle extends BaseRelic{
//     private static final String NAME = "incenseBundle"; 
//     public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
//     private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
//     private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked
//     private AbstractCreature c;

//     public IncenseBundle() {
//         super(ID, NAME, RARITY, SOUND);
//         this.c = c;
//     }

//     public String getUpdatedDescription() {
//         return this.DESCRIPTIONS[0];
//     }

//     public void onExhaust() {
//         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
//         this.c.powers.size();
//         AbstractDungeon.miscRng.randomBoolean();
//             addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.c, this.c, null));
//     }

//     public AbstractRelic makeCopy() {
//     return new IncenseBundle();
//     }
// } 