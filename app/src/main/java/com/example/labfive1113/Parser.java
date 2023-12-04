package com.example.labfive1113;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parseData(InputStream inputStream) {
        List<String> currencyRates = new ArrayList<>();

        try {
            String currencyName = "";
            String rate = "";
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream, "UTF-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("targetCurrency")) {
                    // Extract required information and format the currency rates
                    currencyName = parser.nextText();
                }
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("exchangeRate")) {
                    // Extract required information and format the currency rates
                    rate = parser.nextText();
                    currencyRates.add(currencyName + " - " + rate);
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return currencyRates;
    }
}
