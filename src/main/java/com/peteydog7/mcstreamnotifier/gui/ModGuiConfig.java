/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.gui;

import com.peteydog7.mcstreamnotifier.config.ConfigurationHandler;
import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.reference.Reference;
import cpw.mods.fml.client.config.*;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig{

    public ModGuiConfig(GuiScreen guiScreen){

        super(
                guiScreen,
                getConfigElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()),
                Config.GUI_TITLE
            );

    }

    private static List<IConfigElement> getElements(){

        return new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements();

    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        list.add(categoryElement(Config.CATEGORY_NOTIFICATIONS, "Notifications", Config.CATEGORY_NOTIFICATIONS_LANGKEY));
        list.add(categoryElement(Config.CATEGORY_TWITCH, "Twitch", Config.CATEGORY_TWITCH_LANGKEY));

        return list;
    }

    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(category)).getChildElements());
    }

    /*
    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyCategoryElement(Configuration.CATEGORY_GENERAL, Config.CATEGORY_LANGKEY, NotificationEntry.class));
        list.add(new DummyCategoryElement(Configuration.CATEGORY_GENERAL, Config.CATEGORY_LANGKEY, TwitchEntry.class));

        return list;

    }

    public static class NotificationEntry extends GuiConfigEntries.CategoryEntry
    {
        public NotificationEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
            LogHelper.info("NotificationEntry Constructor");
        }

        @Override
        protected GuiScreen buildChildScreen()
        {
            LogHelper.info("buildChildScreen notification");
            LogHelper.info(new ConfigElement(ConfigurationHandler.configuration.getCategory(Config.CATEGORY_NOTIFICATIONS)).getChildElements());
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's entryList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen,
                    (new ConfigElement(ConfigurationHandler.configuration.getCategory(Config.CATEGORY_NOTIFICATIONS))).getChildElements(),
                    this.owningScreen.modID, Config.CATEGORY_NOTIFICATIONS, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                    GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()), Config.CATEGORY_NOTIFICATIONS_LANGKEY);
        }
    }

    public static class TwitchEntry extends GuiConfigEntries.CategoryEntry
    {
        public TwitchEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
            LogHelper.info("TwtichEntry Constructor");
        }

        @Override
        protected GuiScreen buildChildScreen(){
            LogHelper.info("buildChildScreen twitch");
            LogHelper.info(new ConfigElement(ConfigurationHandler.configuration.getCategory(Config.CATEGORY_TWITCH)).getChildElements());
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's propertyList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen,
                    (new ConfigElement(ConfigurationHandler.configuration.getCategory(Config.CATEGORY_TWITCH))).getChildElements(),
                    this.owningScreen.modID, Config.CATEGORY_TWITCH, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                    GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()), Config.CATEGORY_TWITCH_LANGKEY);
        }
    }

    private static List<IConfigElement> getConfigElements(){

        LogHelper.warn("getConfigElements()");

        List<IConfigElement> main = new ArrayList<IConfigElement>();
        //List<IConfigElement> notification = new ArrayList<IConfigElement>();
        //List<IConfigElement> twitch = new ArrayList<IConfigElement>();

        List<IConfigElement> notificationElements = new ConfigElement(ConfigurationHandler.configuration.getCategory(Config.CATEGORY_NOTIFICATIONS)).getChildElements();
        List<IConfigElement> twitchElements = new ConfigElement(ConfigurationHandler.configuration.getCategory(Config.CATEGORY_TWITCH)).getChildElements();

        main.add((IConfigElement) notificationElements);
        main.add((IConfigElement) twitchElements);

        //main.add(new ConfigElement(new ConfigCategory(Config.CATEGORY_NOTIFICATIONS)));
        //main.add(new ConfigElement(new ConfigCategory(Config.CATEGORY_TWITCH)));

        return main;

    }
    */

}
