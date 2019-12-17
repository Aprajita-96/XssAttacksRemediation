package com.example.musicApp.Security;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.regex.Pattern;
@Component
public class XSSRequestWrapper extends HttpServletRequestWrapper {


        private static Pattern[] patterns = new Pattern[]{
                // Script fragments
                Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
                // src='...'
                Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // lonely script tags
                Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
                Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // eval(...)
                Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // expression(...)
                Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // javascript:...
                Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
                // vbscript:...
                Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
                // onload(...)=...
                Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
        };

     XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);

        }

        @Override
        public String[] getParameterValues(String parameter) {
            String[] values = super.getParameterValues(parameter);
            System.out.println(Arrays.toString(values) +"In parameter method");
            if (values == null) {
                return null;
            }

            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                System.out.println("came inside the loop");
                encodedValues[i] = stripXSS(values[i]);
            }

            return encodedValues;
        }

        @Override
        public String getParameter(String parameter) {
            System.out.println("getting parameters");
            String value = super.getParameter(parameter);
            return stripXSS(value);
        }

        @Override
        public String getHeader(String name) {
            System.out.println("getting header");
            String value = super.getHeader(name);
            System.out.println("In header part2"+value+name);
            return stripXSS(value);
        }

        private String stripXSS(String value) {
            if (value != null) {
                // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
                // avoid encoded attacks.
                // value = ESAPI.encoder().canonicalize(value);

                value = value.replaceAll("", "");

                // Remove all sections that match a pattern
                for (Pattern scriptPattern : patterns){
//                    System.out.println("removing the patterns");
//                    System.out.println(scriptPattern.matcher(value));
                    value = scriptPattern.matcher(value).replaceAll("");
                }
            }
            return value;
        }
    }
