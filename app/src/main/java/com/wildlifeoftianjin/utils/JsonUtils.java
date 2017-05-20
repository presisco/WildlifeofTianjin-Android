package com.wildlifeoftianjin.utils;

import java.util.Stack;

/**
 * Created by presisco on 2017/5/19.
 */

public class JsonUtils {
    public static String getJsonObjectFromMix(String raw) {

        int index = raw.indexOf("{");
        if (index == -1) {
            return "{}";
        }
        int start = index;
        Stack<Character> jsonStack = new Stack<>();
        jsonStack.push('{');
        while (!jsonStack.empty() && index < raw.length()) {
            index++;
            Character probe = raw.charAt(index);
            if (probe == '[' || probe == '{') {
                jsonStack.push(probe);
            } else if (probe == ']' || probe == '}') {
                jsonStack.pop();
            }
        }
        if (jsonStack.empty()) {
            return raw.substring(start, index + 1);
        } else {
            return "{}";
        }
    }

    public static String getJsonArrayFromMix(String raw) {
        int index = raw.indexOf("[");
        if (index == -1) {
            return "[]";
        }
        int start = index;
        Stack<Character> jsonStack = new Stack<>();
        jsonStack.push('[');
        while (!jsonStack.empty() && index < raw.length()) {
            index++;
            Character probe = raw.charAt(index);
            if (probe == '[' || probe == '{') {
                jsonStack.push(probe);
            } else if (probe == ']' || probe == '}') {
                jsonStack.pop();
            }
        }
        if (jsonStack.empty()) {
            return raw.substring(start, index + 1);
        } else {
            return "[]";
        }
    }
}
