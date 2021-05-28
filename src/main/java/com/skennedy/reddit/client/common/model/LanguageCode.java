package com.skennedy.reddit.client.common.model;

/**
 * Represents the possible language codes returned by reddit along with the language's ISO Language name
 * See: https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 */
public enum LanguageCode {

    AF("Afrikaans"),
    AR("Arabic"),
    BG("Bulgarian"),
    BS("Bosnian"),
    CA("Catalan"),
    CS("Czech"),
    DA("Danish"),
    DE("German"),
    EL("Greek"),
    EN("English"),
    EO("Esperanto"),
    ES("Spanish"),
    ET("Estonian"),
    FA("Persian"),
    FI("Finnish"),
    FR("French"),
    GD("Gaelic"),
    GL("Galician"),
    HE("Hebrew"),
    HI("Hindi"),
    HR("Croatian"),
    HU("Hungarian"),
    HY("Armenian"),
    ID("Indonesian"),
    IS("Icelandic"),
    IT("Italian"),
    JA("Japanese"),
    KO("Korean"),
    LA("Latin"),
    LT("Lithuanian"),
    LV("Latvian"),
    NL("Dutch"),
    NN("Norwegian Nynorsk"),
    NO("Norwegian"),
    PL("Polish"),
    PT("Portuguese (Brazil)"),
    PT_PT("Portuguese (Portugal)"),
    RO("Romanian"),
    RU("Russian"),
    SK("Slovak"),
    SL("Slovenian"),
    SR("Serbian"),
    SV("Swedish"),
    TA("Tamil"),
    TH("Thai"),
    TR("Turkish"),
    UK("Ukrainian"),
    VI("Vietnamese"),
    ZH("Chinese");

    private final String language;

    LanguageCode(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
