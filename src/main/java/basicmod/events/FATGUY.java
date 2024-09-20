package basicmod.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;

public class FATGUY extends PhasedEvent{
    public static final String ID = "FATGUY";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    //This text should be set up through loading an event localization json file
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;

    public FATGUY() {
        super(NAME, DESCRIPTIONS[0], "img/events/eventpicture.png");
        
        //This is where you would create your dialog options
        this.imageEventText.setDialogOption(OPTIONS[0]); //This adds the option to a list of options

        registerPhase("Start", new TextPhase("Hi im fat and sorry i hit  oyu").addOption("Option 1", (i)->transitionKey("Phase 2")).addOption("Option 2", (i)->transitionKey("Phase 3")));
    }

    
}