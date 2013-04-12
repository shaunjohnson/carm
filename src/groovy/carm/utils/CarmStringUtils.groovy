package carm.utils

/**
 * CARM application specific string utility methods
 */
class CarmStringUtils {
    static String removeEmptyParagraphs(String htmlString) {
        htmlString ? htmlString.replaceAll(/<p>\s*(&nbsp;)+\s*<\/p>|<p\/>/, "") : htmlString
    }
}
