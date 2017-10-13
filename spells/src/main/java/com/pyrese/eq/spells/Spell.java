package com.pyrese.eq.spells;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class Spell {



    private Map<Field, Object> values;

    private Spell() {
        values = new HashMap<>();
    }

    public <T> T get(Field field) {
        return (T) values.get(field);
    }

    public static Spell deserialize(String content){

    }

    public String serialize(){
        return "";
    }

    /**
     * There are 215 fields available in serialized Spell Data.
     */
    public enum Field {
        ID("id", "unique id used to lookup the spell description in dbstr_us.txt in the Everquest Client", Integer.class, 0),
        NAME("name", "the spell's name", String.class, ""),
        PLAYER_1("player_1", "", String.class, "BLUE_TRAIL"),
        TELEPORT_ZONE("teleport_zone", "The zone you're teleporting to or the npc_types name of the NPC you want to spawn.", String.class, ""),
        YOU_CAST("you_cast", "The message sent to others when you cast the spell.", String.class, ""),
        OTHER_CASTS("other_casts", "The message seen when someone around you casts the spell.", String.class, ""),
        CAST_ON_YOU("cast_on_you", "The message received when the spell is cast on you.", String.class, ""),
        CAST_ON_OTHER("cast_on_other", "unique id used to lookup the spell description in dbstr_us.txt in the Everquest Client", Integer.class, 0),
        SPELL_FADES("spell_fades", "The message recieved when the spell fades.", String.class, ""),
        RANGE("range", "The range of the spell.", Integer.class, 100),
        AOE_RANGE("aoerange", "The range of the spell's area of effect if it is an area of effect spell.", Integer.class, 0),
        PUSH_BACK("pushback", "The push back on the spell.", Integer.class, 0),
        PUSH_UP("pushup", "The push up on the spell.", Integer.class, 0),
        CAST_TIME("cast_time", "Time, in milliseconds, it takes for the spell to cast.", Integer.class, 0),
        RECOVERY_TIME("recovery_time", "The time it takes in between casts of the spell for the spell to recover.", Integer.class, 0),
        RECAST_TIME("recast_time", "The recast time.", Integer.class, 0),
        /**
         * http://www.eqemulator.org/forums/showthread.php?t=38344
         */
        BUFF_DURATION_FORMULA("buffdurationformula", "The formula used to calculate the duration of the spell.", Integer.class, 7),
        BUFF_DURATION("buffduration", "The duration of spell without the buff duration formula added in.", Integer.class, 65),
        AREA_DURATION("AEduration", "The duration of the area of effect.", Integer.class, 0),
        MANA("mana", "Amount of mana required to cast the spell.", Integer.class, 0),
        EFFECT_BASE_VALUE_1("effect_base_value1", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_2("effect_base_value2", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_3("effect_base_value3", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_4("effect_base_value4", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_5("effect_base_value5", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_6("effect_base_value6", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_7("effect_base_value7", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_8("effect_base_value8", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_9("effect_base_value9", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_10("effect_base_value10", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_11("effect_base_value11", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_BASE_VALUE_12("effect_base_value12", "The amount or specific that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_1("effect_limit_value1", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_2("effect_limit_value2", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_3("effect_limit_value3", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_4("effect_limit_value4", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_5("effect_limit_value5", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_6("effect_limit_value6", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_7("effect_limit_value7", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_8("effect_limit_value8", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_9("effect_limit_value9", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_10("effect_limit_value10", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_11("effect_limit_value11", "The maximum amount that is used for the effect id.", Integer.class, 0),
        EFFECT_LIMIT_VALUE_12("effect_limit_value12", "The maximum amount that is used for the effect id.", Integer.class, 0),
        MAX_1("max1", "The maximum effect base value.", Integer.class, 0),
        MAX_2("max2", "The maximum effect base value.", Integer.class, 0),
        MAX_3("max3", "The maximum effect base value.", Integer.class, 0),
        MAX_4("max4", "The maximum effect base value.", Integer.class, 0),
        MAX_5("max5", "The maximum effect base value.", Integer.class, 0),
        MAX_6("max6", "The maximum effect base value.", Integer.class, 0),
        MAX_7("max7", "The maximum effect base value.", Integer.class, 0),
        MAX_8("max8", "The maximum effect base value.", Integer.class, 0),
        MAX_9("max9", "The maximum effect base value.", Integer.class, 0),
        MAX_10("max10", "The maximum effect base value.", Integer.class, 0),
        MAX_11("max11", "The maximum effect base value.", Integer.class, 0),
        MAX_12("max12", "The maximum effect base value.", Integer.class, 0),
        ICON("icon", "The icon ID used for spell gems. Uses new_icon now.", Integer.class, 0),
        MEM_ICON("memicon", "The old icon ID used for spell gems. Uses new_icon now.", Integer.class, 0),
        COMPONENTS_1("components1", "The reagents necessary for the spell.", Integer.class, -1),
        COMPONENTS_2("components2", "The reagents necessary for the spell.", Integer.class, -1),
        COMPONENTS_3("components3", "The reagents necessary for the spell.", Integer.class, -1),
        COMPONENTS_4("components4", "The reagents necessary for the spell.", Integer.class, -1),
        NO_EXPEND_REAGENT_1("NoexpendReagent1", "If it is a number between 1-4 it means component number 1-4 is a focus and not to expend it.\n" +
                "If it is a valid item ID it means this item is a focus as well.", Integer.class, -1),
        NO_EXPEND_REAGENT_2("NoexpendReagent2", "If it is a number between 1-4 it means component number 1-4 is a focus and not to expend it.\n" +
                "If it is a valid item ID it means this item is a focus as well.", Integer.class, -1),
        NO_EXPEND_REAGENT_3("NoexpendReagent3", "If it is a number between 1-4 it means component number 1-4 is a focus and not to expend it.\n" +
                "If it is a valid item ID it means this item is a focus as well.", Integer.class, -1),
        NO_EXPEND_REAGENT_4("NoexpendReagent4", "If it is a number between 1-4 it means component number 1-4 is a focus and not to expend it.\n" +
                "If it is a valid item ID it means this item is a focus as well.", Integer.class, -1),
        /**
         * http://wiki.eqemulator.org/p?Base_Value_Formulas&frm=spells_new
         */
        FORMULA_1("formula1", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_2("formula2", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_3("formula3", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_4("formula4", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_5("formula5", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_6("formula6", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_7("formula7", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_8("formula8", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_9("formula9", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_10("formula10", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_11("formula11", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        FORMULA_12("formula12", "The formula used to determine the way the effect base value scales.", Integer.class, 100),
        LIGHT_TYPE("LightType", "", Integer.class, 0),
        /**
         * Determines whether the spell is beneficial or detrimental.
         0 = Detrimental,
         1 = Beneficial,
         2 = Beneficial, Group Only
         */
        GOOD_EFFECT("goodEffect", "Determines whether the spell is beneficial or detrimental", Integer.class, 0),
        ACTIVATED("activated", "unique id used to lookup the spell description in dbstr_us.txt in the Everquest Client", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Resist_Types&frm=spells_new
         */
        RESIST_TYPE("resisttype", "", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_1("effectid1", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_2("effectid2", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_3("effectid3", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_4("effectid4", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_5("effectid5", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_6("effectid6", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_7("effectid7", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_8("effectid8", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_9("effectid9", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_10("effectid10", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_11("effectid11", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Spell_Effect_IDs&frm=spells_new
         */
        EFFECT_ID_12("effectid12", "\tDetermines what type of effect the effect id has.", Integer.class, 254),
        /**
         * http://wiki.eqemulator.org/p?Target_Types&frm=spells_new
         */
        TARGET_TYPE("targettype", "The target type of the spell.", Integer.class, 2),
        BASE_DIFFICULTY("basediff", "Base difficulty fizzle adjustment.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Skills&frm=spells_new
         */
        SKILL("skill", "Determines the skill used to cast the spell.", Integer.class, 0),
        /**
         * Determines the zone type necessary to cast the spell.

         -1 None
         0 Indoor
         1 Outdoor
         2 Dungeon
         255 Any
         */
        ZONE_TYPE("zonetype", "Determines the zone type necessary to cast the spell.", Integer.class, -1),
        /**
         * Determines the environment type necessary to cast the spell.

         0 = Everywhere
         12 = Cities
         24 = Planes
         */
        ENVIRONMENT_TYPE("EnvironmentType", "Determines the environment type necessary to cast the spell.", Integer.class, 0),
        /**
         * Determines the time of day necessary to cast the spell.
         0 = Any,
         1 = Day Time
         2 = Night Time
         */
        TIME_OF_DAY("TimeOfDay", "Determines the time of day necessary to cast the spell.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_1("classes1", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_2("classes2", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_3("classes3", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_4("classes4", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_5("classes5", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_6("classes6", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_7("classes7", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_8("classes8", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_9("classes9", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_10("classes10", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_11("classes11", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_12("classes12", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_13("classes13", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_14("classes14", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_15("classes15", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Classes&frm=spells_new
         */
        CLASSES_16("classes16", "Defines what level each class can use the spell.", Integer.class, 255),
        /**
         * http://wiki.eqemulator.org/p?Animation_Reference_(DoAnim)
         *
         * Speculation: Should the default be 43 and not 44?
         */
        CASTING_ANIMATION("CastingAnim", "The casting animation.", Integer.class, 44),
        /**
         * http://wiki.eqemulator.org/p?Animation_Reference_(DoAnim)
         */
        TARGET_ANIMATION("TargetAnim", "The target animation.", Integer.class, 13),
        TRAVEL_TYPE("TravelType", "Determines the travel type of the spell.", Integer.class, 0),
        SPELL_AFFECT_INDEX("SpellAffectIndex", "", Integer.class, -1),
        FIELD_124("field124", "unused field", Integer.class, 0),
        FIELD_125("field125", "unused field", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_1("deities1", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_2("deities2", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_3("deities3", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_4("deities4", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_5("deities5", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_6("deities6", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_7("deities7", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         *
        DEITIES_8("deities8", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_9("deities9", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_10("deities10", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_11("deities11", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_12("deities12", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_13("deities13", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_14("deities14", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_15("deities15", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Deity_List&frm=spells_new
         */
        DEITIES_16("deities16", "The same as the deity list, except none for agnostic.", Integer.class, 0),
        FIELD_142("field124", "unused field", Integer.class, 0),
        FIELD_143("field143", "unused field", Integer.class, 0),
        NEW_ICON("new_icon", "The spell's icon.", Integer.class, 161),
        SPELL_ANIMATION("spellanim", "The spell animation.", Integer.class, 0),
        /**
         * Whether or not the spell can be interrupted.
         0 = Interruptable
         1 = Uninterruptable
         */
        UNINTERUPTABLE("uninteruptable", "Whether or not the spell can be interrupted.", Integer.class, 0),
        RESIST_DIFFERENCE("ResistDiff", "The resist difference by default.", Integer.class, -150),
        DOT_STACKING_EXEMPT("dot_stacking_exempt", "Whether or not the damage over time can stack.", Integer.class, 0),
        /**
         * Whether or not the spell is deletable.

         0 = Not Deletable
         1 = Deletable
         */
        DELETEABLE("deleteable", "Whether or not the spell is deletable.", Integer.class, 0),
        RECOURSE_LINK("RecourseLink", "The proc on a spell.", Integer.class, 0),
        FIELD_151("field151", "unused field", Integer.class, 0),
        FIELD_152("field152", "unused field", Integer.class, 0),
        FIELD_153("field153", "unused field", Integer.class, 0),
        /**
         * Whether the spell should be displayed in the short box
         0 = No
         1 = Yes
         */
        SHORT_BUFF_BOX("short_buff_box", "Whether the spell should be displayed in the short box", Integer.class, -1),
        /**
         * http://wiki.eqemulator.org/p?dbstr_us.txt&frm=spells_new
         */
        DESCRIPTION_NUMBER("descnum", "See the type column in  dbstr_us.txt", Integer.class, 0),
        /**
         * The typedescnum value is used client side.
         It corresponds to a value in the client dbstr_us.txt file which is used for categorizing spells in the menu which appears when the user right-click the spell bar.
         This value is also used in conjunction with effectdescnum.
         See the type column in  dbstr_us.txt
         */
        TYPE_DESC_NUM("typedescnum", "used for categorizing spells in the menu which appears when the user right-click the spell bar", Integer.class, 0),
        EFFECT_DESC_NUM("effectdescnum", "See the type column in  dbstr_us.txt", Integer.class, 0),
        FIELD_158("field158", "unused field", Integer.class, 0),
        FIELD_159("field159", "unused field", Integer.class, 0),
        FIELD_160("field160", "unused field", Integer.class, 0),
        FIELD_161("field161", "unused field", Integer.class, 0),
        BONUS_HATE("id", "", Integer.class, 0),
        FIELD_163("field163", "unused field", Integer.class, 100),
        FIELD_164("field164", "unused field", Integer.class, -150),
        FIELD_165("field165", "unused field", Integer.class, 0),
        ENDURANCE_COST("EndurCost", "Endurance used to cast the spell.", Integer.class, 0),
        ENDURANCE_TIMER("EndurTimerIndex", "The time in between endurance costs.", Integer.class, 0),
        FIELD_168("field168", "unused field", Integer.class, 0),
        FIELD_169("field169", "unused field", Integer.class, 0),
        FIELD_170("field170", "unused field", Integer.class, 0),
        FIELD_171("field171", "unused field", Integer.class, 0),
        FIELD_172("field172", "unused field", Integer.class, 0),
        HATE_ADDED("HateAdded", "The amount of hate added by the spell.", Integer.class, 0),
        ENDURANCE_UPKEEP("EndurUpkeep", "The endurance up keep for the spell.", Integer.class, 0),
        /**
         * http://wiki.eqemulator.org/p?Numhit_Types&frm=spells_new
         */
        NUM_HIT_TYPES("field175", "Determines what behavior ticks down the numhits counter.", Integer.class, 0),
        NUM_HITS("numhits", "Counter that ticks down when a specified condition is met.", Integer.class, 0),
        PVP_RESIST_BASE("pvpresistbase", "The resist base in player versus player.", Integer.class, -150),
        PVP_RESIST_CALC("pvpresistcalc", "The calculation for resistances in player versus player.", Integer.class, 100),
        PVP_RESIST_CAP("pvpresistcap", "The maximum resists for player versus player.", Integer.class, -150),
        /**
         * http://wiki.eqemulator.org/p?Spell_Categories&frm=spells_new
         */
        SPELL_CATEGORY("spell_category", "The spell's category.", Integer.class, -99),
        FIELD_181("field181", "unused field", Integer.class, 7),
        FIELD_182("field182", "unused field", Integer.class, 65),
        FIELD_183("field183", "unused field", Integer.class, 0),
        FIELD_184("field184", "unused field", Integer.class, 0),
        /**
         * Determines whether or not the spell can be used as a mass group buff.
         0 = No
         1 = Yes
         */
        MASS_GROUP_BUFF("can_mgb", "Determines whether or not the spell can be used as a mass group buff.", Integer.class, 0),
        /**
         * Determines whether or not the spell can be dispelled.
         0 = Can be dispelled.
         1 = Can be cancelled with a cure, but not dispelled.
         */
        NO_DISPELL("nodispell", "Determines whether or not the spell can be dispelled.", Integer.class, -1),
        /**
         * npc_category
         0 = Non NPC Spell,
         1 = Area of Effect Detrimental,
         2 = Single Target Detrimental,
         3 = Buffs,
         4 = Pet Spells,
         5 = Healing Spells,
         6 = Gate or last cast,
         7 = Debuffs,
         8 = Dispells
         */
        NPC_CATEGORY("npc_category", "npc category", Integer.class, 0),
        NPC_USEFULNESS("npc_usefulness", "Used in conjunction with npc_category. The higher the number, the more useful, the lower the number, the less useful.", Integer.class, 0),
        FIELD_189("field189", "unused field", Integer.class, 0),
        FIELD_190("field190", "unused field", Integer.class, 0),
        FIELD_191("field191", "unused field", Integer.class, 0),
        FIELD_192("field192", "unused field", Integer.class, 0),
        NIMBUS_EFFECT("nimbuseffect", "", Integer.class, 0),
        FIELD_194("field194", "unused field", Integer.class, 0),
        FIELD_195("field195", "unused field", Integer.class, 0),
        FIELD_196("field196", "unused field", Integer.class, 0),
        FIELD_197("field197", "unused field", Integer.class, 0),
        FIELD_198("field198", "unused field", Integer.class, 0),
        FIELD_199("field199", "unused field", Integer.class, 1),
        FIELD_200("field200", "unused field", Integer.class, 0),
        FIELD_201("field201", "unused field", Integer.class, 0),
        FIELD_202("field202", "unused field", Integer.class, 0),
        FIELD_203("field203", "unused field", Integer.class, 0),
        FIELD_204("field204", "unused field", Integer.class, 0),
        FIELD_205("field205", "unused field", Integer.class, 0),
        FIELD_206("field206", "unused field", Integer.class, -1),
        /**
         * http://wiki.eqemulator.org/p?Spell_Groups&frm=spells_new
         */
        SPELL_GROUP("spellgroup", "The spell's group", Integer.class, 0),
        FIELD_208("field208", "unused field", Integer.class, 0),
        FIELD_209("field209", "unused field", Integer.class, 0),
        FIELD_210("field210", "unused field", Integer.class, 1),
        FIELD_211("field211", "unused field", Integer.class, 0),
        ALLOW_REST("allowrest", "", Integer.class, 0),
        FIELD_213("field213", "unused field", Integer.class, 1),
        FIELD_214("field214", "unused field", Integer.class, 1),
        ;

        @Getter
        private String name;
        @Getter
        private String description;
        @Getter
        private Class fieldType;
        @Getter
        private Object defaultValue;

        <T> Field(String name, String description, Class<T> fieldType, T defaultValue) {
            this.name = name;
            this.description = description;
            this.fieldType = fieldType;
            this.defaultValue = defaultValue;
        }
    }

}
