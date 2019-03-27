//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.utils;

import org.apache.commons.lang3.StringEscapeUtils;

public class WailaConfig {
    private final WailaConfig.ConfigGeneral general = new WailaConfig.ConfigGeneral();
    private final WailaConfig.ConfigFormatting formatting = new WailaConfig.ConfigFormatting();

    public WailaConfig() {
    }

    public WailaConfig.ConfigGeneral getGeneral() {
        return this.general;
    }

    public WailaConfig.ConfigFormatting getFormatting() {
        return this.formatting;
    }

    public enum DisplayMode {
        HOLD_KEY,
        TOGGLE;

        DisplayMode() {
        }
    }

    public static class ConfigFormatting {
        private String modName = StringEscapeUtils.escapeJava("§9§o%s");
        private String blockName = StringEscapeUtils.escapeJava("§f%s");
        private String fluidName = StringEscapeUtils.escapeJava("§f%s");
        private String entityName = StringEscapeUtils.escapeJava("§f%s");
        private String registryName = StringEscapeUtils.escapeJava("§7[%s]");

        public ConfigFormatting() {
        }

        public String getModName() {
            return StringEscapeUtils.unescapeJava(this.modName);
        }

        public void setModName(String modName) {
            this.modName = modName;
        }

        public String getBlockName() {
            return StringEscapeUtils.unescapeJava(this.blockName);
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public String getFluidName() {
            return StringEscapeUtils.unescapeJava(this.fluidName);
        }

        public void setFluidName(String fluidName) {
            this.fluidName = fluidName;
        }

        public String getEntityName() {
            return StringEscapeUtils.unescapeJava(this.entityName);
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }

        public String getRegistryName() {
            return StringEscapeUtils.unescapeJava(this.registryName);
        }

        public void setRegistryName(String registryName) {
            this.registryName = registryName;
        }
    }

    public static class ConfigGeneral {
        private boolean displayTooltip = true;
        private boolean shiftForDetails = false;
        private WailaConfig.DisplayMode displayMode;
        private boolean hideFromPlayerList;
        private boolean hideFromDebug;
        private boolean showItem;
        private boolean enableTextToSpeech;
        private int maxHealthForRender;
        private int maxHeartsPerLine;
        private boolean displayFluids;

        public ConfigGeneral() {
            this.displayMode = WailaConfig.DisplayMode.TOGGLE;
            this.hideFromPlayerList = true;
            this.hideFromDebug = true;
            this.showItem = true;
            this.enableTextToSpeech = false;
            this.maxHealthForRender = 40;
            this.maxHeartsPerLine = 10;
        }

        public void setDisplayTooltip(boolean displayTooltip) {
            this.displayTooltip = displayTooltip;
        }

        public void setShiftForDetails(boolean shiftForDetails) {
            this.shiftForDetails = shiftForDetails;
        }

        public void setHideFromPlayerList(boolean hideFromPlayerList) {
            this.hideFromPlayerList = hideFromPlayerList;
        }

        public void setHideFromDebug(boolean hideFromDebug) {
            this.hideFromDebug = hideFromDebug;
        }

        public void setShowItem(boolean showItem) {
            this.showItem = showItem;
        }

        public void setEnableTextToSpeech(boolean enableTextToSpeech) {
            this.enableTextToSpeech = enableTextToSpeech;
        }

        public void setDisplayFluids(boolean displayFluids) {
            this.displayFluids = displayFluids;
        }

        public boolean shouldDisplayTooltip() {
            return this.displayTooltip;
        }

        public boolean shouldShiftForDetails() {
            return this.shiftForDetails;
        }

        public WailaConfig.DisplayMode getDisplayMode() {
            return this.displayMode;
        }

        public void setDisplayMode(WailaConfig.DisplayMode displayMode) {
            this.displayMode = displayMode;
        }

        public boolean shouldHideFromPlayerList() {
            return this.hideFromPlayerList;
        }

        public boolean shouldHideFromDebug() {
            return this.hideFromDebug;
        }

        public boolean shouldShowItem() {
            return this.showItem;
        }

        public boolean shouldEnableTextToSpeech() {
            return this.enableTextToSpeech;
        }

        public int getMaxHealthForRender() {
            return this.maxHealthForRender;
        }

        public void setMaxHealthForRender(int maxHealthForRender) {
            this.maxHealthForRender = maxHealthForRender;
        }

        public int getMaxHeartsPerLine() {
            return this.maxHeartsPerLine;
        }

        public void setMaxHeartsPerLine(int maxHeartsPerLine) {
            this.maxHeartsPerLine = maxHeartsPerLine;
        }

        public boolean shouldDisplayFluids() {
            return this.displayFluids;
        }
    }
}
