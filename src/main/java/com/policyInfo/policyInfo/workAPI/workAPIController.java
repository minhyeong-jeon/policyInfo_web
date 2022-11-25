package com.policyInfo.policyInfo.workAPI;

import com.ctc.wstx.util.ExceptionUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@RestController
public class workAPIController {

    @GetMapping("/apitest")
    public String callApiWithXml() {
        StringBuffer result = new StringBuffer();
        String resultData;
        try {
            String apiUrl = "https://apis.data.go.kr/B554287/NationalWelfareInformations/NationalWelfarelist?" +
                    "serviceKey=CBcCuS23DUJC6B2TfVraEjO8ReHrJF%2FoZfCEn3NxEiKhWB2Of81t%2B9At922MLrK6%2FwDNrVgIE35dMSFpUVKHXA%3D%3D" +
                    "&callTp=L&srchKeyCode=001";

            URL url = new URL(apiUrl);
            System.out.println("apiUrl = " + apiUrl);
            //System.out.println("url = " + url);
            if (url.getProtocol().equals("https")) {    //해당 url이 https이면
                httpsGet(String.valueOf(url));            //ssl인증서를 무시하는 함수를 호출한다.
            }


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //result에 <xmp> </xmp>를 처음과 끝에 달아 준 것은 xml형태 그대로를 브라우저에서 출력해보기 위함
        return result + "</xmp>";
    }

    public static String httpsGet(String strURL) throws Exception

    {
        URL url = null;
        HttpsURLConnection con = null;
        String ret = new String();

        try {
            url = new URL(strURL);
            ignoreSsl();
            con = (HttpsURLConnection)url.openConnection();


            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input = null;

            while ((input = br.readLine()) != null){
                ret += input;
            }

            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return ret;

    }

    public static void ignoreSsl() throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {

                return true;
        }
    };
    trustAllHttpsCertificates();
    HttpsURLConnection.setDefaultHostnameVerifier(hv);

    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }


}
